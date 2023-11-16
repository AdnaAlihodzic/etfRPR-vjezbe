package org.example.ba.unsa.etf.rpr;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;
    private ObjectMapper objectMapper;

    public ArrayList<Laptop> getLaptopi(){return laptopi;}

    public LaptopDaoJSONFile(File file) {
        this.file = file;
        this.objectMapper = new ObjectMapper();
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
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = objectMapper.writeValueAsString(laptopi);
            Files.writeString(Path.of(file.getPath()), json);
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
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException{
        ArrayList<Laptop> lista= new ArrayList<>();
        if (!file.exists()) {
            return lista;
        }
        try {
            String jsonData = Files.readString(Path.of(file.getPath()));

            JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Laptop.class);
            return objectMapper.readValue(jsonData, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
