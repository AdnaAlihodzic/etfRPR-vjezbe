package ba.unsa.etf.rpr.t3.z2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {
    private static Imenik imenik=new Imenik();

    @BeforeAll
    public static void ubaci() throws Exception {
        imenik.Dodaj("Adna Alihodžić", new FiksniBroj(Grad.Sarajevo,"603 640"));
        imenik.Dodaj("Saša Kovačević", new MobilniBroj(61,"230 244"));
        imenik.Dodaj("Dino Merlin",new MedunarodniBroj("Kairo","225 552"));
    }

    @Test
    public void dajBrojNadjen(){
        assertEquals(imenik.dajBroj("Adna Alihodžić"),"032/603 640");
        assertNull(imenik.dajBroj("Nejra"));
    }

    @Test
    public void dajImeTest() throws Exception {
        Imenik imenik = new Imenik();
        FiksniBroj fiksniBroj = new FiksniBroj(Grad.Srajevo,"123321");
        imenik.Dodaj("Izet", fiksniBroj);

        assertEquals("Mirsada", imenik.dajIme(fiksniBroj));
        assertNull(imenik.dajIme(new FiksniBroj(Grad.Sarajevo, "121212")));
    }

    @Test
    public void DajBrojNijeNadjen(){
        String broj=imenik.dajBroj("Elma");
        assertNull(broj);
    }

    @Test
    public void DodajBrojTest(){
        TelefonskiBroj br=new MobilniBroj(61,"778 900");
        imenik.Dodaj("Muhamed", br);
        String broj=imenik.dajBroj("Muhamed");
        assertEquals(broj,"061/778 900");
    }

    @Test
    void toStringTest() throws Exception {
        Imenik imenik = new Imenik();
        imenik.Dodaj("Aldin", new FiksniBroj(Grad.Sarajevo, "123456"));
        imenik.Dodaj("Jamina", new MobilniBroj(61, "654321"));

        String ocekivaniIspis = "1. Selma - 033/123456\n2. Irma - 061/654321\n";
        assertEquals(ocekivaniIspis, imenik.toString());
    }
}


