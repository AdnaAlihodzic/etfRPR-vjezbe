package ba.unsa.etf.rpr.lab9;

import java.util.Scanner;

public class Main {
    public static String ispisiGradove() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        StringBuilder rezultat = new StringBuilder("");
        for (Grad grad : dao.gradovi())
            rezultat.append(grad.getNaziv() + " (" + grad.getDrzava().getNaziv() + ") " + grad.getBrojStanovnika() + "\n");
        return rezultat.toString();
    }

    public static void glavniGrad() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv drzave: ");
        String nazivDrzave = ulaz.nextLine();
        Grad grad = dao.glavniGrad(nazivDrzave);
        if (grad == null)
            System.out.println("Nepostojeca drzava");
        else
            System.out.println("Glavni grad drzave " + nazivDrzave + " je " + grad.getNaziv());
    }

    public static void main(String[] args) {
        System.out.println("Svi gradovi u bazi su: " + ispisiGradove());
        glavniGrad();
    }
}
