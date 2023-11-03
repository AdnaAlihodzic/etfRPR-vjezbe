package ba.unsa.etf.rpr.t3;

import java.util.Objects;

public class MeđunarodniBroj extends TelefonskiBroj{

    private String drzava;
    private String broj;

    public MeđunarodniBroj(String drzava, String broj){
        this.drzava=drzava;
        this.broj=broj;
    }

    public String ispisi(){
        if(drzava!=null && broj!==null){
            return drzava+broj;
        }else{
            return null;
        }
    }

    public int hashCode(){
        return Objects.hash(drzava, broj);
    }
}
