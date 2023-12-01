package ba.unsa.etf.rpr.lv5;

public class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;
    private KolekcijaImena kolekcijaImena;
    Pobjednik(KolekcijaImena kolekcija){
        String pomocni = kolekcija.getNajduzeIme();

        String[] dijelovi = pomocni.split(" ");

        if (dijelovi.length == 2) {
            this.ime = dijelovi[0];
            this.prezime = dijelovi[1];
        } else {
            System.out.println("Neispravan format stringa.");
        }
        this.brojZnakova = this.ime.length();
    }

}