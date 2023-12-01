package ba.unsa.etf.rpr.lv5;

public class Ocjena {
    private LicneInformacije osoba;
    private int ocjena;
    public Ocjena(LicneInformacije osoba, int ocjena) {
        this.osoba = osoba;
        this.ocjena = ocjena;
    }
    public void setOcjena(int ocjena) {
        if(ocjena>0 && ocjena<10)
            this.ocjena = ocjena;
    }
}
