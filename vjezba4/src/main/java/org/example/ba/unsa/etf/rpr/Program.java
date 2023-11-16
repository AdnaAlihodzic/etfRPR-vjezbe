package org.example.ba.unsa.etf.rpr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Program {
    public static void main(String[] args){
        LaptopDao laptopS = new LaptopDaoSerializableFile(new File("s-file.txt"));
        LaptopDao laptopX = new LaptopDaoXMLFile(new File("x.xml"));
        LaptopDao laptopJ = new LaptopDaoJSONFile(new File("j.json"));

        Laptop l1= new Laptop();
        setSveAtributeLaptopa(l1, "Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop();
        setSveAtributeLaptopa(l2, "HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        Laptop l3=new Laptop();
        setSveAtributeLaptopa(l3, "HP", "Pavilion", 800.0, 12, 256, 0, "i5", "Intel", 14.0);

        try{
            laptopS.dodajLaptopUFile(l1);
            ArrayList<Laptop> lista= laptopX.vratiPodatkeIzDatoteke();
            laptopJ.napuniListu(lista);
            laptopX.dodajLaptopUFile(l3);
            Laptop l4= laptopX.getLaptop("i5");
            laptopJ.dodajLaptopUFile(l4);
        }
        catch(NeodgovarajuciProcesorException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void setSveAtributeLaptopa(Laptop laptop, String b, String m, double c, int ram, int hdd, int ssd, String proc, String graf, double vel) {
        laptop.setBrend(b);
        laptop.setModel(m);
        laptop.setCijena(c);
        laptop.setProcesor(proc);
        laptop.setRam(ram);
        laptop.setHdd(hdd);
        laptop.setSsd(ssd);
        laptop.setGrafickaKartica(graf);
        laptop.setVelicinaEkrana(vel);
    }
}
