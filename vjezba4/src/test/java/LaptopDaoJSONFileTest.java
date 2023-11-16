
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LaptopDaoJSONFileTest {


    private static LaptopDaoJSONFile laptopJ = new LaptopDaoJSONFile(new File("j.test.json"));
    public static void pocetniPodaci(){

        Laptop l1=new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop("HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        laptopJ.dodajLaptopUFile(l1);
        laptopJ.dodajLaptopUFile(l2);

    }


    @org.junit.jupiter.api.Test
    void dodajLaptopUFile() {
        Laptop lap=new Laptop("HP", "Spectre", 1600.0, 8, 256, 0,"i7", "Intel", 14.0);
        laptopJ.dodajLaptopUFile(lap);
        try {
            assertEquals(lap, laptopJ.getLaptop("i7"));
        }
        catch(NeodgovarajuciProcesorException e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getLaptop() {
        String procesor="nnn";
        assertThrows(NeodgovarajuciProcesorException.class, ()->{laptopJ.getLaptop(procesor);});
    }

    @org.junit.jupiter.api.Test
    void napuniListu() {
        Laptop l1=new Laptop("HP", "Spectre", 1600.0, 8, 256, 0,"i7", "Intel", 14.0);
        Laptop l2=new Laptop("HP", "Pavilion", 800.0, 12, 256, 0, "i5", "Intel", 14.0);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();
        testLista.add(l1);
        testLista.add(l2);
        laptopJ.napuniListu(testLista);
        assertEquals(2, laptopJ.getLaptopi().size());
    }

    @org.junit.jupiter.api.Test
    void vratiPodatkeIzDatoteke() {
        LaptopDaoJSONFile LDJ= new LaptopDaoJSONFile(new File("JFILE.json"));
        Laptop l1=new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop("HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        LDJ.dodajLaptopUFile(l1);
        LDJ.dodajLaptopUFile(l2);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();
        try {
            testLista = LDJ.vratiPodatkeIzDatoteke();
            assertEquals(2, testLista.size());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}