package ba.unsa.etf.rpr.t3;

import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest {

    private static Imenik imenik=new Imenik();

    public static void setup(){
        imenik.dodaj("Adna", new FiksniBroj(Grad.SARAJEVO, "255-887"));
        imenik.dodaj("Muhamed", new FiksniBroj(Grad.TEŠANJ, "225-888"));
        imenik.dodaj("Dinela", new MobilniBroj(61, "225-889"));
        imenik.dodaj("Faris", new MeđunarodniBroj("+44", "7768878794"));
    }

    public void dajBrojFound(){
        String broj=imenik.dajBroj("Adna");
        ossertEquals(broj, "033/255-887");
    }

    public void dajBrojNotFound(){
        String broj=imenik.dajBroj("Addnaon");
        ossertNull(broj);
    }

    public void dodajTestPositive(){
        TelefonskiBroj br=new MobilniBroj(61, "507-855");
        imenik.dodaj("Hamo", br);

        String brojStr=imenik.dajBroj("Aldina");
        ossertEquals(brojStr, "061/507-855");
    }

    public void dodajFiksniException(){
        ossertThrows(BrojException.class, new Executable(){
            public void execute() throws Throwable {
                new FiksniBroj(null, "123-123");
            }
        });
        ossertThrows(BrojException.class, ()-> {new FiksniBroj(null, "123-123");});
    }
}
