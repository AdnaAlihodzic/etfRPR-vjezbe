package org.example.ba.unsa.etf.rpr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
@JacksonXmlRootElement(localName = "Laptop")
public class Laptop implements Serializable{
    private static final long serialVersionUID = -4455558404501025282L;
    public Laptop(){};

    private String brend;
    private String model;
    private double cijena;
    private int ram;
    private int hdd;
    private int ssd;
    private String procesor;
    private String grafickaKartica;
    private double velicinaEkrana;

    public Laptop(String brend, String model, double cijena, int ram, int hdd, int ssd, String procesor, String grafickaKartica, double velicinaEkrana) {
        this.brend = brend;
        this.model = model;
        this.cijena = cijena;
        this.ram = ram;
        this.hdd = hdd;
        this.ssd = ssd;
        this.procesor = procesor;
        this.grafickaKartica = grafickaKartica;
        this.velicinaEkrana = velicinaEkrana;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public void setGrafickaKartica(String grafickaKartica) {
        this.grafickaKartica = grafickaKartica;
    }

    public void setVelicinaEkrana(double velicinaEkrana) {
        this.velicinaEkrana = velicinaEkrana;
    }

    @JacksonXmlProperty(localName = "brend")
    public String getBrend() {
        return brend;
    }

    @JacksonXmlProperty(localName = "model")
    public String getModel() {
        return model;
    }

    @JacksonXmlProperty(localName = "cijena")
    public double getCijena() {
        return cijena;
    }

    @JacksonXmlProperty(localName = "ram")
    public int getRam() {
        return ram;
    }

    @JacksonXmlProperty(localName = "hdd")
    public int getHdd() {
        return hdd;
    }

    @JacksonXmlProperty(localName = "ssd")
    public int getSsd() {
        return ssd;
    }

    @JacksonXmlProperty(localName = "grafickaKartica")
    public String getGrafickaKartica() {
        return grafickaKartica;
    }

    @JacksonXmlProperty(localName = "velicinaEkrana")
    public double getVelicinaEkrana() {
        return velicinaEkrana;
    }

    @JacksonXmlProperty(localName = "procesor")
    public String getProcesor() {return procesor;}

}
