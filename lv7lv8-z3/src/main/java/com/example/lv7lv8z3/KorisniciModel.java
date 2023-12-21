package com.example.lv7lv8z3;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> listaKorisnika;
    private SimpleObjectProperty<Korisnik> trenutniKorisnik;

    public void napuni() {
        listaKorisnika.add(new Korisnik("Adna", "Alihodžić", "adna1@gmail.com", "adna1", "adna123"));
        listaKorisnika.add(new Korisnik("Nejra", "Vraneš", "nejra2@gmail.com", "nejra2", "nejra234"));
        listaKorisnika.add(new Korisnik("Emina", "Imamović", "emina2@gmail.com", "emina3", "emina345"));
        listaKorisnika.add(new Korisnik("Anida", "Gagula", "anida4@gmail.com", "anida4", "anida456"));
        listaKorisnika.add(new Korisnik("Ema", "Husić", "ema5@gmail.com", "ema5", "ema567"));
    }

    public KorisniciModel() {
        listaKorisnika = FXCollections.observableArrayList();
        napuni();
        trenutniKorisnik = new SimpleObjectProperty<>(listaKorisnika.get(0));
    }

    public ObservableList<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public void setListaKorisnika(ObservableList<Korisnik> l) {
        listaKorisnika = l;
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public void setTrenutniKorisnik(Korisnik k) {
        this.trenutniKorisnik.set(k);
    }
}
