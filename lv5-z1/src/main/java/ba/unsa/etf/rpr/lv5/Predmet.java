package ba.unsa.etf.rpr.lv5;

public class Predmet implements Predstavi {
    private String naziv;
    private String opis;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String predstavi() {
        return naziv+", "+opis;
    }
}