package com.example.sweets.models;
import java.sql.*;
import java.util.ArrayList;


public class DB {
    final String URL = "jdbc:mysql://localhost/sweets";
    final String USERNAME = "root";
    final String PASSWORD = "tara1001";
    private Connection connection;
    public static Gift gift = null;
    public static Sweet sweet = null;

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Gift> getAllGifts() {
        ArrayList<Gift> gifts = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT gift.id, color.name, box.name  FROM sweets.gift join box on gift.box_id=box.id join color on gift.color_id= color.id ; ");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String color = resultSet.getString(2);
                String box = resultSet.getString(3);
                System.out.printf("%d, %s, %s \n ", id, color, box);
                Gift gift = new Gift(id, Box.valueOf(box), Color.valueOf(color));
                gifts.add(gift);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gifts;
    }

    public void addGift(Gift gift) {

        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(String.format(" SELECT color.id FROM color where color.name= \"%s\"", gift.getColor().name()));
            int color_id = -1;
            if (resultSet.next()) {
                color_id = resultSet.getInt(1);
            }
            ResultSet resultSet1 = statement1.executeQuery(String.format("SELECT box.id FROM box where box.name=\"%s\"", gift.getBox().name()));
            int box_id = -1;
            if (resultSet1.next()) {
                box_id = resultSet1.getInt(1);
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO `sweets`.`gift` (`color_id`, `box_id`) VALUES (%d, %d);", color_id, box_id));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteGift(Gift gift) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM gift WHERE ID = %d", gift.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateGift(Gift gift) {
        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(String.format(" SELECT color.id FROM color where color.name= \"%s\"", gift.getColor().name()));
            int color_id = -1;
            if (resultSet.next()) {
                color_id = resultSet.getInt(1);
            }
            ResultSet resultSet1 = statement1.executeQuery(String.format("SELECT box.id FROM box where box.name=\"%s\"", gift.getBox().name()));
            int box_id = -1;
            if (resultSet1.next()) {
                box_id = resultSet1.getInt(1);
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE gift SET color_id = %d, box_id = %d where id = %d", color_id, box_id, gift.getId()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   public ArrayList<Sweet> getSweets(int giftId) {
        ArrayList<Sweet> sweets = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT gift_has_biscuit.Id, name, sugar, price, amount FROM sweets.gift_has_biscuit join Sweet on biscuit_id = Sweet.Id where gift_Id=" + giftId);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double sugar = resultSet.getDouble(3);
                double price = resultSet.getDouble(4);
                int amount = resultSet.getInt(5);
                Biscuit b = new Biscuit(id, name, sugar, price, amount);
                sweets.add(b);
            }
            resultSet = statement.executeQuery(" SELECT gift_has_candy.Id, name, sugar, price, weight FROM sweets.gift_has_candy join Sweet on candy_id = Sweet.Id where gift_Id= " + giftId);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double sugar = resultSet.getDouble(3);
                double price = resultSet.getDouble(4);
                double weight = resultSet.getDouble(5);
                Candy c = new Candy(id, name, sugar, price, weight);
                sweets.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sweets;
    }
    public ArrayList<Sweet>getAllSweet() {
        ArrayList<Sweet>sweets= new ArrayList<>();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT Sweet.Id, name, sugar, price,isCandy FROM  Sweet " );
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double sugar = resultSet.getDouble(3);
                double price = resultSet.getDouble(4);
                boolean isCandy = resultSet.getBoolean(5);
                System.out.println(id);
                if(isCandy){
                sweets.add(new Candy(id, name,sugar, price, 0));
                }
                else{
                   sweets.add( new Biscuit(id, name,sugar, price, 0));
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sweets;
    }
    public void addCandy (int giftId, Candy c) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(" INSERT INTO `sweets`.`gift_has_candy` (`candy_id`, `gift_id`, `weight`) VALUES ( %d, %d, %f ) ", c.getId(), giftId, c.getWeight()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addBiscuit (int giftId, Biscuit b) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(" INSERT INTO `sweets`.`gift_has_biscuit` ( `gift_id`, `biscuit_id`, `amount`) VALUES ( %d, %d, %d ) ", giftId, b.getId(), b.getAmount()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteCandy(int giftId, int candyId) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM gift_has_candy WHERE id = %d", candyId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteBiscuit(int giftId, int biscuitId) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM gift_has_biscuit WHERE id = %d", biscuitId));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void editCandy(Candy candy ) {
        try {
            String query = "UPDATE `sweets`.`gift_has_candy` SET `weight` = ? WHERE (`id` = ?)";
            PreparedStatement statement = connection.prepareStatement(query);//дає встановити нам значення
            statement.setDouble(1,candy.getWeight());
            statement.setInt(2, candy.getId());
            statement.executeUpdate();
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }
    public void editBiscuit(Biscuit biscuit) {
        try {
            String query = "UPDATE `sweets`.`gift_has_biscuit` SET `amount` = ? WHERE (`id` = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,biscuit.getAmount());
            statement.setInt(2, biscuit.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
