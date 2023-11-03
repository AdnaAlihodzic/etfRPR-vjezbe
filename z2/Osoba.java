package ba.unsa.etf.rpr.t2.z2;

public class Osoba {
    private String ime, prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return "Osoba{" + "ime='" + ime + '\'' + ", prezime='" + prezime + '\'' + '}';
    }
}
