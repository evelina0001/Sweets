package com.example.sweets.models;
import java.util.ArrayList;

public class Gift {

    private Box box;
    private Color color;
    private int id;
    private ArrayList<Sweet> present;

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Gift(Box box, Color color) {
        this.box = box;
        this.color = color;
    }

    public Gift(int id, Box box, Color color) {
        this.id = id;
        this.box = box;
        this.color = color;
        present = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Sweet> getSweets() {
        return present;
    }


    @Override
    public String toString() {
        return "Gift{" +
                "Box = " + box +
                ", color = " + color +
                '}';
    }


    public ArrayList<Sweet> findBySugar(double min, double max) {
        ArrayList<Sweet> sweets = new ArrayList<>();
        for (Sweet sw : present) {
            if (sw.getSugar() >= min && sw.getSugar() <= max) {
                sweets.add(sw);
            }
        }
        return sweets;
    }

    public ArrayList<Sweet> findByPrice(double min, double max) {
        ArrayList<Sweet> sweets = new ArrayList<>();
        for (Sweet sw : present) {
            if (sw.getPrice() >= min && sw.getPrice() <= max) {
                sweets.add(sw);
            }
        }
        return sweets;
    }

    public ArrayList<Sweet> findByName(String nim) {
        ArrayList<Sweet> sweets = new ArrayList<>();
        for (Sweet sw : present) {
            if (sw.getName().toLowerCase().contains(nim.toLowerCase())) {
                sweets.add(sw);
            }
        }
        return sweets;
    }
    public double countGiftSugar() {
        double sugar = 0;
        for (Sweet s : present) {
            sugar += s.getSugar();
        }
        return sugar;
    }

    public double countGiftPrice() {
        double price = 0;
        for(Sweet sw : present) {
            if("Candy".equals(sw.getClass().getSimpleName())) {
                Candy c = (Candy) sw;
               price += sw.getPrice() * c.getWeight()/1000;
            } else {
                Biscuit b = (Biscuit) sw;
                price += sw.getPrice() * b.getAmount();
            }
        }
        return price;
    }
}