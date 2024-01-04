package ba.unsa.etf.rpr.lv10lv11;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class DrzavaController {
    @FXML
    public TextField fieldNaziv;
    @FXML
    public ChoiceBox<String> choiceGrad;
    @FXML
    public Button btnOk;
    @FXML
    public Button btnCancel;
    private ObservableList<String> glavniGradovi = FXCollections.observableArrayList();
    private GeografijaDAO geografija;
    private GlavnaController glctrl;

    private boolean dodaj = false;
    private boolean izmijeni = false;
    private boolean obrisi = false;

    public boolean isDodaj() {
        return dodaj;
    }

    public void setDodaj(boolean dodaj) {
        this.dodaj = dodaj;
    }

    public boolean isIzmijeni() {
        return izmijeni;
    }

    public void setIzmijeni(boolean izmijeni) {
        this.izmijeni = izmijeni;
    }

    public boolean isObrisi() {
        return obrisi;
    }

    public void setObrisi(boolean obrisi) {
        this.obrisi = obrisi;
    }

    public DrzavaController(GeografijaDAO geo){
        geografija = geo;
    }

    public DrzavaController(GeografijaDAO geo, GlavnaController glctrl){
        geografija = geo;
        this.glctrl = glctrl;
    }

    @FXML
    public void initialize(){
        List<Grad> tmp = geografija.gradovi();
        for(var x : tmp){
            glavniGradovi.add(x.getNaziv());
        }
        choiceGrad.setItems(glavniGradovi);
        choiceGrad.setOnAction(event -> {
            izabranGlavniGrad();
        });

        fieldNaziv.textProperty().bindBidirectional(geografija.getTrenutnaDrzava().nazivProperty());
        geografija.trenutnaDrzavaProperty().addListener((obs, oldDrzava, newDrzava) -> {
            if(oldDrzava != null){
                fieldNaziv.textProperty().unbindBidirectional(oldDrzava.nazivProperty());
            }
            if(newDrzava != null){
                fieldNaziv.textProperty().bindBidirectional(newDrzava.nazivProperty());
            }
        });
    }
    @FXML
    public void izabranGlavniGrad(){
        geografija.getTrenutnaDrzava().setGlavni_grad(choiceGrad.getValue());
    }

    @FXML
    public void onBtnOkClick(){
        if(isDodaj()){
            geografija.dodajDrzavu(geografija.getTrenutnaDrzava());
        }
        else if(isIzmijeni()){
            geografija.izmijeniDrzavu(geografija.getTrenutnaDrzava());
        }
        else if(isObrisi()){
            geografija.obrisiDrzavu(geografija.getTrenutnaDrzava().getNaziv());
        }
    }
    @FXML
    public void onBtnCancelClick(ActionEvent event){
        glctrl.refreshTableViewGradovi();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

