package ba.unsa.etf.rpr.lv5;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KolekcijaPoruka {
    private List<String> poruke;

    public KolekcijaPoruka(List<Predstavi> p){
        for(int i=0; i<p.size(); i++){
            poruke.add(p.get(i).predstavi());
        }
    }

    public List<String> getPoruke() {
        return poruke;
    }


}