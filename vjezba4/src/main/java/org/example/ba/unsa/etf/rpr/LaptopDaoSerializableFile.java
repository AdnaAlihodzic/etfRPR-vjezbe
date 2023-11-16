package org.example.ba.unsa.etf.rpr;

import java.io.*;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao {
    private File file;
    private ArrayList<Laptop> laptopi;

    public ArrayList<Laptop> getLaptopi(){return laptopi;}

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();

    }

    private void readObjectNoData()
            throws ObjectStreamException {
        System.out.println("Nema podataka");
    }


    public LaptopDaoSerializableFile(File file) {
        this.file = file;
        if (file.exists()) {
            try (ObjectInputStream ulaz = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ulaz.readObject();
                if (obj instanceof ArrayList) {
                    laptopi = (ArrayList<Laptop>) obj;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        else laptopi = new ArrayList<>();
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            ObjectOutputStream izlaz = new ObjectOutputStream(new FileOutputStream(file));
            izlaz.writeObject(laptop);
        }
        catch(IOException e){
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

    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        try {
            ObjectInputStream ulaz = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Laptop> lista = new ArrayList<>();

            while (true) {
                try {
                    Laptop l = (Laptop) ulaz.readObject();
                    lista.add(l);
                } catch (EOFException e) {
                    break;
                }
            }

            ulaz.close();
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
