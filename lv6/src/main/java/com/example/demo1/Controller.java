package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private SimpleStringProperty displej;
    private List<Double> brojevi = new ArrayList<>();
    private List<String> operacije = new ArrayList<>();
    private boolean biloJednako = false;

    public SimpleStringProperty displejProperty() {
        return displej;
    }

    public String getDisplej() {
        return displej.get();
    }

    public Controller() {
        displej = new SimpleStringProperty("0");
    }

    public void Kec(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("1");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("1");
        else displej.set(displej.get() + "1");
    }

    public void Dvica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("2");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("2");
        else displej.set(displej.get() + "2");
    }

    public void Trica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("3");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("3");
        else displej.set(displej.get() + "3");
    }

    public void Cetvorka(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("4");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("4");
        else displej.set(displej.get() + "4");
    }

    public void Petica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("5");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("5");
        else displej.set(displej.get() + "5");
    }

    public void Sestica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("6");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("6");
        else displej.set(displej.get() + "6");
    }

    public void Sedmica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("7");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("7");
        else displej.set(displej.get() + "7");
    }

    public void Osmica(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("8");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("8");
        else displej.set(displej.get() + "8");
    }

    public void Devetka(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("9");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("9");
        else displej.set(displej.get() + "9");
    }

    public void Modul(ActionEvent actionEvent) {
        if(getDisplej().equals("")){
            operacije.remove(operacije.size()-1);
        }
        else {
            brojevi.add(Double.valueOf(getDisplej()));
            displej.setValue("");
        }
        operacije.add("%");
    }

    public void Dijeljenje(ActionEvent actionEvent) {
        if(getDisplej().equals("")){
            operacije.remove(operacije.size()-1);
        }
        else {
            brojevi.add(Double.valueOf(getDisplej()));
            displej.setValue("");
        }
        operacije.add("/");
    }

    public void Mnozenje(ActionEvent actionEvent) {
        if(getDisplej().equals("")){
            operacije.remove(operacije.size()-1);
        }
        else {
            brojevi.add(Double.valueOf(getDisplej()));
            displej.setValue("");
        }
        operacije.add("*");
    }

    public void Oduzimanje(ActionEvent actionEvent) {
        if(getDisplej().equals("")){
            operacije.remove(operacije.size()-1);
        }
        else {
            brojevi.add(Double.valueOf(getDisplej()));
            displej.setValue("");
        }
        operacije.add("-");
    }

    public void Sabiranje(ActionEvent actionEvent) {
        if(getDisplej().equals("")){
            operacije.remove(operacije.size()-1);
        }
        else {
            brojevi.add(Double.valueOf(getDisplej()));
            displej.setValue("");
        }
        operacije.add("+");
    }

    public void Jednako(ActionEvent actionEvent) {

        biloJednako = true;
        brojevi.add(Double.valueOf(getDisplej()));
//            operacije.stream().forEach(System.out::println);
//            brojevi.stream().forEach(System.out::println);
        displej.setValue(Izracunaj());
        brojevi.clear();
        operacije.clear();
    }

    private String Izracunaj() {
        //operacije.stream().forEach(System.out::println);
        while(brojevi.size() != 1) {
            //brojevi.stream().forEach(System.out::println);
            if (operacije.contains("%")) {
                int index = operacije.indexOf("%");
                Double prvi = brojevi.get(index);
                Double drugi = brojevi.get(index + 1);
                Double rez = prvi % drugi;
                List<Double> pom = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    pom.add(brojevi.get(i));
                }
                pom.add(rez);
                for (int i = index + 2; i < brojevi.size(); i++) {
                    pom.add(brojevi.get(i));
                }
                brojevi.clear();
                brojevi = pom;
                pom = null;
                operacije.remove(index);
            }
            else if (operacije.contains("*")) {
                int index = operacije.indexOf("*");
                Double prvi = brojevi.get(index);
                Double drugi = brojevi.get(index + 1);
                Double rez = prvi * drugi;
                List<Double> pom = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    pom.add(brojevi.get(i));
                }
                pom.add(rez);
                for (int i = index + 2; i < brojevi.size(); i++) {
                    pom.add(brojevi.get(i));
                }
                brojevi.clear();
                brojevi = pom;
                pom = null;
                operacije.remove(index);
            }
            else if (operacije.contains("/")) {
                int index = operacije.indexOf("/");
                Double prvi = brojevi.get(index);
                Double drugi = brojevi.get(index + 1);
                Double rez = prvi / drugi;
                List<Double> pom = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    pom.add(brojevi.get(i));
                }
                pom.add(rez);
                for (int i = index + 2; i < brojevi.size(); i++) {
                    pom.add(brojevi.get(i));
                }
                brojevi.clear();
                brojevi = pom;
                pom = null;
                operacije.remove(index);
            }
            else if (operacije.contains("-")) {
                int index = operacije.indexOf("-");
                Double prvi = brojevi.get(index);
                Double drugi = brojevi.get(index + 1);
                Double rez = prvi - drugi;
                List<Double> pom = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    pom.add(brojevi.get(i));
                }
                pom.add(rez);
                for (int i = index + 2; i < brojevi.size(); i++) {
                    pom.add(brojevi.get(i));
                }
                brojevi.clear();
                brojevi = pom;
                pom = null;
                operacije.remove(index);
            }
            else if (operacije.contains("+")) {
                int index = operacije.indexOf("+");
                Double prvi = brojevi.get(index);
                Double drugi = brojevi.get(index + 1);
                Double rez = prvi + drugi;
                List<Double> pom = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    pom.add(brojevi.get(i));
                }
                pom.add(rez);
                for (int i = index + 2; i < brojevi.size(); i++) {
                    pom.add(brojevi.get(i));
                }
                brojevi.clear();
                brojevi = pom;
                pom = null;
                operacije.remove(index);
            }
        }
        return String.valueOf(brojevi.get(0));
    }

    public void Nula(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("0");
            biloJednako = false;
        }
        else if(displej.get().equals("0"))
            displej.setValue("0");
        else displej.set(displej.get() + "0");
    }

    public void Tacka(ActionEvent actionEvent) {
        if(biloJednako){
            displej.setValue("0");
            biloJednako = false;
        }
        else if(displej.get().equals("")){
            displej.setValue("0");
        }
        else if(displej.get().equals("0"))
            displej.setValue("0");
        displej.set(displej.get() + ".");
    }
}