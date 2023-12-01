package ba.unsa.etf.rpr.lv5;

public class InformacijeONastavniku extends LicneInformacije implements Predstavi {
    private String ime;
    private String prezime;
    private String titula;

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
}