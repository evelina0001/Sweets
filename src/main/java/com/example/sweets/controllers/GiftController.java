package com.example.sweets.controllers;
import com.example.sweets.models.DB;
import com.example.sweets.models.Gift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GiftController implements Initializable {
    @FXML
    private TableView<Gift> Table;
    @FXML
    private TableColumn<Gift, String> idColumn;
    @FXML
    private TableColumn<Gift, String> colorColumn;
    @FXML
    private TableColumn<Gift, String> boxColumn;
    @FXML
    Controller controller = new Controller();

    @FXML
    protected void addGift(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/addGift.fxml");
    }
    @FXML
    protected void deleteGift(ActionEvent event) throws IOException {
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/deleteGift.fxml");
    }
    @FXML
    protected void editGift(ActionEvent event) throws IOException {
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/editGift.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.setGiftCells(idColumn, colorColumn, boxColumn);
        DB db = new DB();
        ObservableList<Gift>gifts = FXCollections.observableArrayList(db.getAllGifts());
        ArrayList<Gift>allGifts= db.getAllGifts();
        for (Gift g:allGifts) {
            System.out.println(g.getId());
        }
        Table.setItems(gifts);
    }
}
