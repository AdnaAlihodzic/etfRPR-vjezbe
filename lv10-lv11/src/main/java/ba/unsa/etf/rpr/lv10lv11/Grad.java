package ba.unsa.etf.rpr.lv10lv11;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad {
    private SimpleStringProperty drzava;
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty brojStanovnika;


    public Grad(){
        naziv = new SimpleStringProperty("");
        brojStanovnika = new SimpleIntegerProperty(0);
        drzava = new SimpleStringProperty("");
    }
    public Grad(String n, int b, String d){
        naziv = new SimpleStringProperty(n);
        brojStanovnika = new SimpleIntegerProperty(b);
        drzava = new SimpleStringProperty(d);
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

    public int getBrojStanovnika() {
        return brojStanovnika.get();
    }

    public SimpleIntegerProperty brojStanovnikaProperty() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika.set(brojStanovnika);
    }

    public String getDrzava() {
        return drzava.get();
    }

    public SimpleStringProperty drzavaProperty() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava.set(drzava);
    }

    @Override
    public String toString() {
        return naziv + " (" + drzava + ") - " + brojStanovnika;
    }
}