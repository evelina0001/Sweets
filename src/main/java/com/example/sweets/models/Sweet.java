package com.example.sweets.models;

public class Sweet implements Cloneable{
    private int id;
    private String name;
    private double sugar;
    private double price;


    public Sweet(int id, double sugar, double price, String  name) {
        this.name = name;
        this.sugar = sugar;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String info() {
        return String.format("%s\n%f\n%f\n%s\n", this.getClass().getSimpleName(), this.sugar, this.price, this.name);
    }

    public Sweet(String name, double sugar, double price) {
        this.sugar = sugar;
        this.price = price;
        this.name = name;
    }

    public Sweet() {
        name = "Korivka";
        price = 100;
        sugar = 20;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        try {
            if (name == null || name.equals("")) {
                throw new Exception("invalid data");
            } else {
                this.name = name;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.name = "no name";
        }
    }
    public double getSugar() {
        return  sugar;
    }
    public double setSugar(double sugar) {
        try {
            if (sugar < 0){
                throw new Exception("invalid value of sugar");
            } else {
                this.sugar = sugar;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.sugar = 10;
        }
        return sugar;
    }
    public double getPrice() {
        return price;
    }
    public  void  setPrice(double price) {
        try {
            if (price <= 0) {
                throw new Exception("invalid value of price");
            } else {
                this.price = price;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.price = 30;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", sugar: " + sugar +
                ", price: " + price ;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
