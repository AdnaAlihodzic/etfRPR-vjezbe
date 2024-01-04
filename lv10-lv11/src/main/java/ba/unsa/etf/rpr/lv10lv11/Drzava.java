package ba.unsa.etf.rpr.lv10lv11;

import javafx.beans.property.SimpleStringProperty;

public class Drzava {
    private SimpleStringProperty naziv;
    private SimpleStringProperty glavni_grad;

    public Drzava(){
        naziv = new SimpleStringProperty("");
        glavni_grad = new SimpleStringProperty("");
    }
    public Drzava(String n, String gg){
        naziv = new SimpleStringProperty(n);
        glavni_grad = new SimpleStringProperty(gg);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public String getGlavni_grad() {
        return glavni_grad.get();
    }

    public SimpleStringProperty glavni_gradProperty() {
        return glavni_grad;
    }

    public void setGlavni_grad(String glavni_grad) {
        this.glavni_grad.set(glavni_grad);
    }

    @Override
    public String toString() {
        return naziv + " - " + glavni_grad;
    }
}
