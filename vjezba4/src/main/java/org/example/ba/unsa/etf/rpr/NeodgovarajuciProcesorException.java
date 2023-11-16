package org.example.ba.unsa.etf.rpr;

public class NeodgovarajuciProcesorException extends Exception{
    private String poruka;
    public NeodgovarajuciProcesorException(String poruka){
        super(poruka);
    }
}
