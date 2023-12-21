package com.example.lv7lv8z3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class HelloController{
    private KorisniciModel model = new KorisniciModel();
    public ListView listViewKorisnika = new ListView<>(model.getListaKorisnika());
    private boolean poljeJeIspravno;
    public Button dodajButton;
    public Button krajButton;
    public TextField imeText;
    public TextField prezimeText;
    public TextField emailText;
    public TextField kImeText;
    public PasswordField lozinkaPassword;

    private boolean ispravnostPolja(TextField nekiTekst) {
        nekiTekst.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String stari, String novi) {
                if(nekiTekst.getText().trim().isEmpty()) {
                    nekiTekst.getStyleClass().removeAll("poljeIspravno");
                    nekiTekst.getStyleClass().add("poljeNijeIspravno");
                    poljeJeIspravno = false;
                } else {
                    nekiTekst.getStyleClass().removeAll("poljeNijeIspravno");
                    nekiTekst.getStyleClass().add("poljeIspravno");
                    poljeJeIspravno = true;
                }
            }
        });
        return poljeJeIspravno;
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
                    poljeJeIspravno = false;
                } else {
                    lozinkaPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    lozinkaPassword.getStyleClass().add("poljeIspravno");
                    poljeJeIspravno = true;
                }
            }
        });
        listViewKorisnika.setItems(model.getListaKorisnika());
    }

    @FXML
    public void exitOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)krajButton.getScene().getWindow();
        stage.close();
    }

    public void dodajOnClick(ActionEvent actionEvent) {
        if(!poljeJeIspravno) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Podaci nisu ispravni");
            alert.setHeaderText("Niste pravilno popunili podatke, stoga se ovaj korisnik ne moze dodati");
            alert.show();
            return;
        }
        model.getListaKorisnika().add(new Korisnik(imeText.getText(), prezimeText.getText(),
                emailText.getText(), kImeText.getText(), lozinkaPassword.getText()));
        //listViewKorisnika.refresh();
    }

    public void listViewMouseClick(MouseEvent mouseEvent) {
        Korisnik izabraniKorisnik = (Korisnik) listViewKorisnika.getSelectionModel().getSelectedItem();
        model.setTrenutniKorisnik(izabraniKorisnik);
    }
}