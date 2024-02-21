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
    protected void onFindClick(ActionEvent event) throws  IOException {
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event,"src/main/resources/com/example/sweets/find-by-name-view.fxml" );
    }

    @FXML
    protected void addGift(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/add-gift-view.fxml");
    }
    @FXML
    protected void deleteGift(ActionEvent event) throws IOException {
        if(Table.getSelectionModel().getSelectedItem() == null) {
            controller.showMessage("Select gift to delete");
            return;
        }
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/delete-gift-view.fxml");
    }
    @FXML
    protected void findBySugar(ActionEvent event) throws IOException {
        if (Table.getSelectionModel().getSelectedItem() == null) {
            controller.showMessage("Select gift to edit");
            return;
        }
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/find-by-sugar-view.fxml");
    }

    @FXML
    protected void editGift(ActionEvent event) throws IOException {
        if (Table.getSelectionModel().getSelectedItem() == null) {
            controller.showMessage("Select gift to edit");
            return;
        }
        DB.gift = Table.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/edit-gift-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.setGiftCells(idColumn, colorColumn, boxColumn);
        DB db = new DB();
        ObservableList<Gift>gifts = FXCollections.observableArrayList(db.getAllGifts());
        Table.setItems(gifts);
    }
}
