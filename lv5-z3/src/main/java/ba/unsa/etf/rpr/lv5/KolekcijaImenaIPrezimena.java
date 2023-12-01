package ba.unsa.etf.rpr.lv5;

import java.util.List;

public class KolekcijaImenaIPrezimena {
    private List<String> imena;
    private List <String> prezimena;

    int getIndexNajduzegPara(){
        int max = 0, maxi = 0;
        for(int i = 1; i < imena.size() && i < prezimena.size(); i++){
            if(imena.get(i).length() + prezimena.get(i).length() > max){
                max = imena.get(i).length() + prezimena.get(i).length();
                maxi = i;
            }
        }
        return maxi;
    }
    String getImeIprezime(int i){
        return imena.get(i)+" " + prezimena.get(i);
    }
}