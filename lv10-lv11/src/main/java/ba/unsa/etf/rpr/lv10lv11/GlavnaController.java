package ba.unsa.etf.rpr.lv10lv11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GlavnaController {
    @FXML
    public TableView<Grad> tableViewGradovi;
    @FXML
    public TableColumn<Grad, String> nazivCol;
    @FXML
    public TableColumn<Grad, Integer> stanovnikaCol;
    @FXML
    public TableColumn<Grad, String> drzavaCol;
    @FXML
    public Button btnDodajGrad;
    @FXML
    public Button btnDodajDrzavu;
    @FXML
    public Button btnIzmijeniGrad;
    @FXML
    public Button btnIzmijeniDrzavu;
    @FXML
    public Button btnObrisiGrad;
    @FXML
    public Button btnObrisiDrzavu;
    private ObservableList<Grad> gradovi = FXCollections.observableArrayList();

    private GeografijaDAO geo = GeografijaDAO.getInstance();

    @FXML
    public void initialize(){
        List<Grad> tmp = geo.gradovi();
        for(var x : tmp){
            gradovi.add(new Grad(x.getNaziv(), x.getBrojStanovnika(), x.getDrzava()));
        }
        tableViewGradovi.setItems(gradovi);
    }

    public void refreshTableViewGradovi(){
        List<Grad> tmp = geo.gradovi();
        ObservableList<Grad> newGradovi = FXCollections.observableArrayList();
        for(var x : tmp){
            newGradovi.add(new Grad(x.getNaziv(), x.getBrojStanovnika(), x.getDrzava()));
        }
        tableViewGradovi.setItems(newGradovi);
        gradovi = newGradovi;
    }
    @FXML
    public void onbtnDodajDrzavuClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("drzava.fxml"));
            DrzavaController dctrl = new DrzavaController(geo, this);
            dctrl.setDodaj(true);
            fxmlLoader.setController(dctrl);
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setTitle("Drzava");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onbtnDodajGradClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("grad.fxml"));
            GradController gctrl = new GradController(geo, this);
            gctrl.setDodaj(true);
            fxmlLoader.setController(gctrl);
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();
            stage.setTitle("Grad");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onbtnIzmijeniGradClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("grad.fxml"));
            GradController gctrl = new GradController(geo, this);
            gctrl.setIzmijeni(true);
            fxmlLoader.setController(gctrl);
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();
            stage.setTitle("Grad");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onbtnIzmijeniDrzavuClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("drzava.fxml"));
            DrzavaController dctrl = new DrzavaController(geo, this);
            dctrl.setIzmijeni(true);
            fxmlLoader.setController(dctrl);
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setTitle("Drzava");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onbtnObrisiGradClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("grad.fxml"));
            GradController gctrl = new GradController(geo, this);
            gctrl.setObrisi(true);
            fxmlLoader.setController(gctrl);
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();
            stage.setTitle("Grad");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onbtnObrisiDrzavuClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(GeoApplication.class.getResource("drzava.fxml"));
            DrzavaController dctrl = new DrzavaController(geo, this);
            dctrl.setObrisi(true);
            fxmlLoader.setController(dctrl);
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();

            /*stage.setOnCloseRequest(event -> { //??
                refreshTableViewGradovi()
            });*/

            stage.setTitle("Drzava");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}