package com.example.lv7lv8z4;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class HelloController {
    private KorisniciModel model = new KorisniciModel();
    private boolean popunjeno;
    public ListView listViewKorisnika = new ListView<>(model.getListaKorisnika());
    public Button dodajButton;
    public Button krajButton;
    public TextField imeText;
    public TextField prezimeText;
    public TextField emailText;
    public TextField kImeText;
    public PasswordField lozinkaPassword;

    private void daLiSuPoljaPopunjena() {
        Korisnik zadnji = model.getListaKorisnika().get(model.getListaKorisnika().size() - 1);
        popunjeno = !zadnji.imeProperty().get().trim().isEmpty() &&
                !zadnji.prezimeProperty().get().trim().isEmpty() &&
                !zadnji.emailProperty().get().trim().isEmpty() &&
                !zadnji.kImeProperty().get().trim().isEmpty() &&
                !zadnji.lozinkaProperty().get().trim().isEmpty();
    }
    private void ispravnostPolja(TextField nekiTekst) {
        nekiTekst.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String stari, String novi) {
                if(nekiTekst.getText().trim().isEmpty()) {
                    nekiTekst.getStyleClass().removeAll("poljeIspravno");
                    nekiTekst.getStyleClass().add("poljeNijeIspravno");
                } else {
                    nekiTekst.getStyleClass().removeAll("poljeNijeIspravno");
                    nekiTekst.getStyleClass().add("poljeIspravno");
                }
                daLiSuPoljaPopunjena();
            }
        });
    }

    @FXML
    public void initialize() {
        model.trenutniKorisnikProperty().addListener( (obs, oldKorisnik, newKorisnik) -> {
            if(oldKorisnik != null) {
                imeText.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                prezimeText.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                emailText.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                kImeText.textProperty().unbindBidirectional(oldKorisnik.kImeProperty());
                lozinkaPassword.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
            }
            if(newKorisnik != null) {
                imeText.textProperty().bindBidirectional(newKorisnik.imeProperty());
                prezimeText.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                emailText.textProperty().bindBidirectional(newKorisnik.emailProperty());
                kImeText.textProperty().bindBidirectional(newKorisnik.kImeProperty());
                lozinkaPassword.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
            }
        });
        ispravnostPolja(imeText);
        ispravnostPolja(prezimeText);
        ispravnostPolja(emailText);
        ispravnostPolja(kImeText);
        lozinkaPassword.textProperty().addListener( new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String oV, String nV) {
                if(lozinkaPassword.getText().trim().isEmpty()) {
                    lozinkaPassword.getStyleClass().removeAll("poljeIspravno");
                    lozinkaPassword.getStyleClass().add("poljeNijeIspravno");
                } else {
                    lozinkaPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    lozinkaPassword.getStyleClass().add("poljeIspravno");
                }
                daLiSuPoljaPopunjena();
            }
        });
        model.getTrenutniKorisnik().imeProperty().addListener((obs, oV, nV) -> daLiSuPoljaPopunjena());
        model.getTrenutniKorisnik().prezimeProperty().addListener((obs, oV, nV) -> daLiSuPoljaPopunjena());
        model.getTrenutniKorisnik().emailProperty().addListener((obs, oV, nV) -> daLiSuPoljaPopunjena());
        model.getTrenutniKorisnik().kImeProperty().addListener((obs, oV, nV) -> daLiSuPoljaPopunjena());
        model.getTrenutniKorisnik().lozinkaProperty().addListener((obs, oV, nV) -> daLiSuPoljaPopunjena());
        listViewKorisnika.setItems(model.getListaKorisnika());
        listViewKorisnika.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik((Korisnik)newKorisnik);
            listViewKorisnika.refresh();
        });
    }

    @FXML
    public void exitOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)krajButton.getScene().getWindow();
        stage.close();
    }

    public void dodajOnClick(ActionEvent actionEvent) {
        if(popunjeno) {
            Korisnik k = new Korisnik();
            model.getListaKorisnika().add(k);
            int zadnjiElement = listViewKorisnika.getItems().size() - 1;
            listViewKorisnika.getSelectionModel().select(zadnjiElement);
            listViewKorisnika.getFocusModel();
            listViewKorisnika.scrollTo(zadnjiElement);
            model.setTrenutniKorisnik(k);

            listViewKorisnika.getSelectionModel().selectedIndexProperty().addListener((obs, oV, nV) -> {
                System.out.println("\n\tOld value: " + oV + " new value: " + nV + " popunjena: " + popunjeno);
                System.out.println("\n\tOld value.intVal: " + oV.intValue() + " new value int: " + nV.intValue() + " popunjena: " + popunjeno);
                if (nV.intValue() != model.getListaKorisnika().size() && !oV.equals(nV) && !popunjeno) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Podaci nisu ispravno popunjeni");
                    alert.setHeaderText("Niste pravilno popunili podatke, stoga se ovaj korisnik ne moze dodati");
                    alert.setContentText("Morate pravilno popuniti svako polje date forme");
                    alert.showAndWait();
                    listViewKorisnika.getFocusModel();
                    listViewKorisnika.scrollTo(zadnjiElement);
                    model.setTrenutniKorisnik(k);
                }
            });
        }
    }

}