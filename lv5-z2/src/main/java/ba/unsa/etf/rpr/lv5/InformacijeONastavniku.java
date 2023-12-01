package ba.unsa.etf.rpr.lv5;

import java.util.ArrayList;
import java.util.List;

public class InformacijeONastavniku extends LicneInformacije implements Predstavi, MozeOcijeniti {
    private String ime;
    private String prezime;
    private String titula;
    private List<Ocjena> listaOcjena=new ArrayList<Ocjena>();

    public List<Ocjena> getListaOcjena() {
        return listaOcjena;
    }

    public void setListaOcjena(List<Ocjena> listaOcjena) {
        this.listaOcjena = listaOcjena;
    }
    public void dodajOcjenu(Ocjena o){listaOcjena.add(o);}

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public String predstavi() {
        return ime+" "+prezime+", "+titula;
    }

    @Override
    public Ocjena ocijeni(int x) {
        return new Ocjena(this, x);
    }
}