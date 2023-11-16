

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaptopDaoXMLFileTest {
    private static LaptopDaoXMLFile laptopX = new LaptopDaoXMLFile(new File("test.xml"));

    void dodajLaptopUFile() {

        XmlMapper XMLMock = mock(XmlMapper.class);
        File fileMock = mock(File.class);

        LaptopDaoXMLFile LDS = new LaptopDaoXMLFile(fileMock);
        LDS.setXMLMapper(XMLMock);

        Laptop laptop = new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);

        try {
            when(LDS.vratiPodatkeIzDatoteke()).thenReturn(new ArrayList<>());
            LDS.dodajLaptopUFile(laptop);

            verify(XMLMock).writeValueAsString(Mockito.any());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLaptop() {
        String procesor="nnn";
        assertThrows(NeodgovarajuciProcesorException.class, ()->{laptopX.getLaptop(procesor);});
    }

    @org.junit.jupiter.api.Test
    void napuniListu() {
        Laptop l1=new Laptop("HP", "Spectre", 1600.0, 8, 256, 0,"i7", "Intel", 14.0);
        Laptop l2=new Laptop("HP", "Pavilion", 800.0, 12, 256, 0, "i5", "Intel", 14.0);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();
        testLista.add(l1);
        testLista.add(l2);
        laptopX.napuniListu(testLista);
        assertEquals(2, laptopX.getLaptopi().size());
    }


    void vratiPodatkeIzDatoteke() {
        LaptopDaoXMLFile LDX= new LaptopDaoXMLFile(new File("xt.xml"));
        Laptop l1=new Laptop("Dell", "XPS", 1500.0, 16, 512, 256, "i7", "Nvidia", 15.6);
        Laptop l2=new Laptop("HP", "Envy", 1200.0, 8, 256, 0, "i5", "Intel", 13.3);
        LDX.dodajLaptopUFile(l1);
        LDX.dodajLaptopUFile(l2);
        ArrayList<Laptop> testLista=new ArrayList<Laptop>();

        try {
            testLista = LDX.vratiPodatkeIzDatoteke();
            assertEquals(2, testLista.size());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}