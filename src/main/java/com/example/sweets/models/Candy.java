package com.example.sweets.models;


public class Candy extends Sweet {
    private double weight;

    public Candy(int id, String name, double sugar, double price, double weight) {
        super(id, sugar, price, name);
        this.weight = weight;
    }

    public String info() {
        return super.info() + weight + "\n";
    }

   public  double getWeight() {
       return weight;
   }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return super.toString() + ", type: Candy"
                + ", weight: " + weight;
    }
  }
