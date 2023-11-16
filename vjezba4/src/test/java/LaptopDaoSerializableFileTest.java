import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LaptopDaoSerializableFileTest {
    private static LaptopDaoSerializableFile laptopS = new LaptopDaoSerializableFile(new File("test-file.txt"));
    public static void pocetniPodaci(){

        Laptop l1=new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop("HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        laptopS.dodajLaptopUFile(l1);
        laptopS.dodajLaptopUFile(l2);

    }


    @org.junit.jupiter.api.Test
    void dodajLaptopUFile() {
        Laptop lap=new Laptop("HP", "Spectre", 1600.0, 8, 256, 0,"i7", "Intel", 14.0);
        laptopS.dodajLaptopUFile(lap);
        try {
            assertEquals(lap, laptopS.getLaptop("i7"));
        }
        catch(NeodgovarajuciProcesorException e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getLaptop() {
        String procesor="nnn";
        assertThrows(NeodgovarajuciProcesorException.class, ()->{laptopS.getLaptop(procesor);});
    }

    @org.junit.jupiter.api.Test
    void napuniListu() {
        Laptop l1=new Laptop("HP", "Spectre", 1600.0, 8, 256, 0,"i7", "Intel", 14.0);
        Laptop l2=new Laptop("HP", "Pavilion", 800.0, 12, 256, 0, "i5", "Intel", 14.0);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();
        testLista.add(l1);
        testLista.add(l2);
        laptopS.napuniListu(testLista);
        assertEquals(2, laptopS.getLaptopi().size());
    }

    @org.junit.jupiter.api.Test
    void vratiPodatkeIzDatoteke() {
        LaptopDaoSerializableFile LDS= new LaptopDaoSerializableFile(new File("tf.txt"));
        Laptop l1=new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop("HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        LDS.dodajLaptopUFile(l1);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();
        testLista=LDS.vratiPodatkeIzDatoteke();
        assertEquals(1, testLista.size());
    }

}
