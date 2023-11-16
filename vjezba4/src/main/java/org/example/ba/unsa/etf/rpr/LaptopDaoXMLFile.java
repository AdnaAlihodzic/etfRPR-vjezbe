package org.example.ba.unsa.etf.rpr;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;
    private XmlMapper XMLMapper;

    public void setXMLMapper(XmlMapper XMLMapper) {
        this.XMLMapper = XMLMapper;
    }
    public ArrayList<Laptop> getLaptopi(){return laptopi;}

    public LaptopDaoXMLFile(File file) {
        this.file = file;
        this.XMLMapper = new XmlMapper();
        if (file.exists()) {
            this.laptopi = new ArrayList<Laptop>();
            try {
                laptopi = vratiPodatkeIzDatoteke();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        } else {
            this.laptopi = new ArrayList<Laptop>();
        }
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            laptopi.add(laptop);
            XMLMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String xml = XMLMapper.writeValueAsString(laptopi);
            Files.writeString(Path.of(file.getPath()), xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        for(Laptop l:laptopi){
            if(l.getProcesor().equals(procesor)) return l;
        }
        throw new NeodgovarajuciProcesorException("Ne postoji laptop sa navedenim procesorom!");
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi=laptopi;
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException {
        ArrayList<Laptop> lista= new ArrayList<>();
        if (!file.exists()) {
            return lista;
        }
        try {
            String xmlData = Files.readString(Path.of(file.getPath()));

            JavaType type = XMLMapper.getTypeFactory().constructCollectionType(ArrayList.class, Laptop.class);
            return XMLMapper.readValue(xmlData, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
