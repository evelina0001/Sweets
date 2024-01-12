package com.example.sweets.controllers;
import com.example.sweets.models.Biscuit;
import com.example.sweets.models.Candy;
import com.example.sweets.models.Gift;
import com.example.sweets.models.Sweet;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.util.Scanner;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
public class Controller {

    public void goTo(ActionEvent event , String path) throws IOException {
        URL url = new File(path).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setCells
            //Встановлюємо рядки
            (TableColumn<Sweet, String> id,
             TableColumn<Sweet, String> name,
             TableColumn<Sweet, String> price,
             TableColumn<Sweet, String> sugar,
             TableColumn<Sweet, String> amount) {
        id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getId() + ""));
        name.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        price.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.format("%.2f", cellData.getValue().getPrice())));
        sugar.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.format("%.2f", cellData.getValue().getSugar())));
        amount.setCellValueFactory(cellData -> {
            double value=0;
            if(cellData.getValue().getClass().getSimpleName().equals("Candy")){
                Candy c = (Candy) cellData.getValue();
                value= c.getWeight();
                return new ReadOnlyStringWrapper(String.format("%.2f",value));
            }
            else{
                Biscuit b = (Biscuit) cellData.getValue();
                value= b.getAmount();
                return new ReadOnlyStringWrapper(String.format("%d",(int)value));
            }
        });
    }

    public void setGiftCells
            //Встановлюємо рядки для подарунку
            (TableColumn<Gift, String> id,
             TableColumn<Gift, String> color,
             TableColumn<Gift, String> box) {
        id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.format(" %d ", cellData.getValue().getId())));
        color.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getColor().name()));
        box.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBox().name()));
    }


    public boolean isNumeric(String strNum) {
         Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        //Визначаємо шаблон для чисел, які можуть бути цілими або десятковими,
        // включаючи можливість від'ємних чисел.
        if (strNum == null) {
            return false;    }
        return pattern.matcher(strNum).matches();
    }
    public void showMessage(String message) {
        //Цей код створює об'єкт Alert з типом повідомлення про помилку
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);

        alert.show();
    }

}
