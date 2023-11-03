package ba.unsa.etf.rpr.t2.z2;

public class Racun {
    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private double stanjeRacuna;

    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
    }

    private boolean provjeriOdobrenjePrekoracenja() {
        return odobrenjePrekoracenja;
    }

    public void izvrsiUplatu(double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }

    public void odobriPrekoracenje(double prekoracenje) {
        odobrenjePrekoracenja = true;
    }
}

