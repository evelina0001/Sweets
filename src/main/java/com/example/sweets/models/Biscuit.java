package com.example.sweets.models;

public  class Biscuit extends Sweet {
    private int amount;

    public Biscuit(int id, String name, double sugar, double price, int amount) {
        super(id, sugar, price, name);
        this.amount = amount;
    }
    public  Biscuit() {
        amount = 129;
    }
     public int getAmount() {
        return amount;
     }
     public void setAmount(double amount) {
        this.amount = (int) amount;
     }
    public String info() {
        return super.info() + amount + "\n";
    }
    @Override
     public String toString() {
         return super.toString() + ", type: Biscuit, "
                 +  "amount: " + amount;
     }

}
