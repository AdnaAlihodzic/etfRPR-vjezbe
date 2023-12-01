package ba.unsa.etf.rpr.lv5;

import java.util.List;

public class Predmet implements Predstavi {
    private String naziv;
    private String opis;
    private List<Ocjena> listaOcjena;

    public List<Ocjena> getListaOcjena() {
        return listaOcjena;
    }

    public void setListaOcjena(List<Ocjena> listaOcjena) {
        this.listaOcjena = listaOcjena;
    }
    public void dodajOcjenu(Ocjena o){listaOcjena.add(o);}

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
