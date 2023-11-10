package ba.unsa.etf.rpr.t3.z3;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {

    private static Imenik im = new Imenik();

    @BeforeEach
    void setUp() {
        im.dodaj("Adna", new FiksniBroj(Grad.BIHAC, "221-037"));
        im.dodaj("Nejra", new FiksniBroj(Grad.SARAJEVO, "232-673"));
        im.dodaj("Amina", new MobilniBroj(76, "292-111"));
        im.dodaj("Nejla", new MobilniBroj(21, "255-795"));
        im.dodaj("Ajla", new MedunarodniBroj("+23", "4542533"));
        im.dodaj("Zehra", new MedunarodniBroj("+23", "8773723"));
    }
    public void testMock(){
        Imenik i= Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Adna")).thenReturn("Nista");

        String test=i.dajBroj("Adna");
        assertEquals(test,"Nema nista");
    }

    public void testMock2(){
        Map<String,TelefonskiBroj> mapa=Mockito.mock(Map.class);
        Mockito.when(mapa.get("Adna")).thenReturn(new MobilniBroj(34,"221-037"));
        im.setBrojevi(mapa);

        String br=im.dajBroj("Adna");
        assertNotEquals(br,"221-037");

    }

}
