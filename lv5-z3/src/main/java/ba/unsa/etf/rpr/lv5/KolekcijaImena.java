package ba.unsa.etf.rpr.lv5;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaImena {
    List<String> kolekcija;
    public KolekcijaImena() {
        this.kolekcija = new ArrayList<>();
    }

    public void dodajImeIPrezime(String imePrezime) {
        this.kolekcija.add(imePrezime);
    }

    public String getNajduzeIme() {
        String najduzeIme = "";

        for (String ime : kolekcija) {
            if (ime.length() > najduzeIme.length()) {
                najduzeIme = ime;
            }
        }

        return najduzeIme;
    }

}