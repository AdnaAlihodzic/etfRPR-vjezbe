package com.example.lv7lv8z2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    private boolean poljeJeIspravno = false;
    private Map<String, String[]> mapa = new HashMap<>();
    private Korisnik korisnik = new Korisnik();
    public Button dodajButton;
    @FXML
    private ListView<String> listaKorisnika;
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
        imeText.textProperty().bindBidirectional(korisnik.imeProperty());
        prezimeText.textProperty().bindBidirectional(korisnik.prezimeProperty());
        emailText.textProperty().bindBidirectional(korisnik.emailProperty());
        kImeText.textProperty().bindBidirectional(korisnik.kImeProperty());
        lozinkaPassword.textProperty().bindBidirectional(korisnik.lozinkaProperty());

        ispravnostPolja(imeText);
        ispravnostPolja(prezimeText);
        ispravnostPolja(emailText);
        ispravnostPolja(kImeText);
        korisnik.lozinkaProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> oV, String stari, String novi){
                if (lozinkaPassword.getText().isEmpty()) {
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

        String[] noviKorisnik = {korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(),
                korisnik.getkIme(), korisnik.getLozinka()};
        if(mapa.containsKey(korisnik.getIme())) {
            return;
        } else {
            mapa.put(korisnik.getIme(), noviKorisnik);
            listaKorisnika.getItems().add(korisnik.getIme());
        }
//        String[] noviKorisnik = {(String)imeText.getText(), (String)prezimeText.getText(),
//                (String)emailText.getText(), (String)kImeText.getText(), (String)lozinkaPassword.getText()};
//        mapa.put((String)imeText.getText(), noviKorisnik);
//        listaKorisnika.getItems().add(imeText.getText());
    }

    public void listViewMouseClick(MouseEvent mouseEvent) {
        String izabraniElement = listaKorisnika.getSelectionModel().getSelectedItem();
        if(izabraniElement != null) {
            korisnik.setIme(mapa.get(izabraniElement)[0]);
            korisnik.setPrezime(mapa.get(izabraniElement)[1]);
            korisnik.setEmail(mapa.get(izabraniElement)[2]);
            korisnik.setkIme(mapa.get(izabraniElement)[3]);
            korisnik.setLozinka(mapa.get(izabraniElement)[4]);
        }
    }
}