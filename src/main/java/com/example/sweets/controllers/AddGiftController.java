package com.example.sweets.controllers;
import com.example.sweets.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddGiftController implements Initializable {
    Controller controller = new Controller();
    @FXML
    private ComboBox<Color> colorBox;
    @FXML
    private ComboBox<Box> boxBox;
    @FXML
    protected void onGoBackClick(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/Gift-View.fxml");
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxBox.setItems(FXCollections.observableArrayList(Box.values())); //Можливі варіанти вибору відображають усі значення, визначені в перерахуванні Box.
        colorBox.setItems(FXCollections.observableArrayList(Color.values()));
    }
    @FXML
    protected void addGift(ActionEvent event) throws IOException { //Створюємо подарунок
        if (boxBox.getValue() == null ) {
            controller.showMessage("Choose box");
            return;
        }
        if (colorBox.getValue() == null) {
            controller.showMessage("Choose color");
            return;
        }
        Gift gift = new Gift(boxBox.getValue(), colorBox.getValue());
        DB db = new DB();
        db.addGift(gift);
        controller.goTo(event, "src/main/resources/com/example/sweets/Gift-View.fxml");

    }
}
