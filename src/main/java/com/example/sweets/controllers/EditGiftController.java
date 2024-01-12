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

public class EditGiftController implements Initializable {
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
        //Цей метод ініціалізує випадаючі списки або комбобокси значеннями з перерахувань Box і Color,
        // А також встановлює значення за замовчуванням для кольору та упаковки, які отримані з об'єкта DB.gift.
        boxBox.setItems(FXCollections.observableArrayList(Box.values()));
        colorBox.setItems(FXCollections.observableArrayList(Color.values()));
        colorBox.setValue(DB.gift.getColor());
        boxBox.setValue(DB.gift.getBox());
    }

    @FXML
    protected void editGift(ActionEvent event) throws IOException {
        DB.gift.setBox(boxBox.getValue());
        DB.gift.setColor(colorBox.getValue());
        DB db = new DB();
        db.updateGift(DB.gift);
        controller.goTo(event,"src/main/resources/com/example/sweets/Gift-View.fxml");

    }
    @FXML
    protected void editSweets(ActionEvent event) throws IOException {

        controller.goTo(event, "src/main/resources/com/example/sweets/sweet-View.fxml");
}
}

