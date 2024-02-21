package com.example.sweets.models;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void AddSweet(Sweet sweet) {
        for (Sweet s : present ) {
            if (s.getName().equals(sweet.getName())) {
                if (sweet.getClass().getSimpleName().equals("Candy")) {
                    Candy c = (Candy)sweet;
                    Candy candy = (Candy)s;
                    candy.setWeight(candy.getWeight() + c.getWeight());
                } else {
                    Biscuit b = (Biscuit)sweet;
                    Biscuit biscuit = (Biscuit)s;
                    biscuit.setAmount(b.getAmount() + biscuit.getAmount());
                }
                System.out.println(s);
               return;
            }
        }
        present.add(sweet);
        System.out.println(sweet);
    }

    @Override
    public String toString() {
        return "Gift{" +
                "Box = " + box +
                ", color = " + color +
                '}';
    }

    public void read(String filename) {
        try {
            File sweet = new File(filename);
            Scanner scan = new Scanner(sweet);
            box = Box.valueOf(scan.next());
            color = Color.valueOf(scan.next());
            int FileSize = scan.nextInt();
            present.clear();
            for (int i = 0; i < FileSize; i++) {
                String type = scan.next();
                double sugar = scan.nextDouble();
                double price = scan.nextDouble();
                String name = scan.next();
                if (type.equals("Biscuit")) {
                    int amount = scan.nextInt();
                    present.add(new Biscuit(id, name, sugar, price, amount));
                } else if (type.equals("Candy")) {
                    double weight = scan.nextDouble();
                    present.add(new Candy(id, name, sugar, price, weight));
                } else if (type.equals("Sweet")) {
                    present.add(new Sweet(name, sugar, price));

                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public void setPresent(ArrayList<Sweet> present) {
        this.present = present;
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