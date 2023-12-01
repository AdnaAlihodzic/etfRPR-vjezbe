package ba.unsa.etf.rpr.lv5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InformacijeONastavniku n1=new InformacijeONastavniku();
        Scanner ulaz=new Scanner(System.in);
        String i1=new String();
        String p1=new String();
        String t1=new String();
        System.out.print("Unesite ime nastavnika: ");
        i1=ulaz.nextLine();
        System.out.print("Unesite prezime nastavnika: ");
        p1=ulaz.nextLine();
        System.out.print("Unesite titulu nastavnika: ");
        t1=ulaz.nextLine();
        n1.setIme(i1);
        n1.setPrezime(p1);
        n1.setTitula(t1);

        InformacijeOStudentu s1=new InformacijeOStudentu();
        String i2=new String();
        String p2=new String();
        String gs=new String();
        String brI=new String();
        System.out.print("Unesite ime studenta: ");
        i2=ulaz.nextLine();
        System.out.print("Unesite prezime studenta: ");
        p2=ulaz.nextLine();
        System.out.print("Unesite godinu studija: ");
        gs=ulaz.nextLine();
        System.out.print("Unesite broj indexa: ");
        brI=ulaz.nextLine();

        s1.setIme(i2);
        s1.setPrezime(p2);
        s1.setGodinaStudija(gs);
        s1.setBrojIndexa(brI);

        System.out.println("Nastavnik: "+n1.getIme()+" "+n1.getPrezime()+", "+n1.getTitula());
        System.out.println("Student: "+s1.getIme()+" "+s1.getPrezime()+", "+s1.getGodinaStudija()+". godina studija, broj indexa: "+s1.getBrojIndexa());

    }
}
