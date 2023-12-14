package com.example.lv7lv8z1;

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
    public Button dodajButton;
    @FXML
    private ListView<String> listaKorisnika;
    public Button krajButton;
    public TextField imeText;
    public TextField prezimeText;
    public TextField emailText;
    public TextField kImeText;
    public PasswordField lozinkaPassword;

    private void ispravnostPolja(TextField nekiTekst) {
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
    }

    @FXML
    public void initialize() {
        ispravnostPolja(imeText);
        ispravnostPolja(prezimeText);
        ispravnostPolja(emailText);
        ispravnostPolja(kImeText);
        lozinkaPassword.textProperty().addListener(new ChangeListener<String>() {
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

        String[] noviKorisnik = {(String)imeText.getText(), (String)prezimeText.getText(),
                (String)emailText.getText(), (String)kImeText.getText(), (String)lozinkaPassword.getText()};
        mapa.put((String)imeText.getText(), noviKorisnik);
        listaKorisnika.getItems().add(imeText.getText());
    }

    public void listViewMouseClick(MouseEvent mouseEvent) {
        String izabraniElement = listaKorisnika.getSelectionModel().getSelectedItem();
        if(izabraniElement != null) {
            imeText.setText(mapa.get(izabraniElement)[0]);
            prezimeText.setText(mapa.get(izabraniElement)[1]);
            emailText.setText(mapa.get(izabraniElement)[2]);
            kImeText.setText(mapa.get(izabraniElement)[3]);
            lozinkaPassword.setText(mapa.get(izabraniElement)[4]);
        }
    }
}