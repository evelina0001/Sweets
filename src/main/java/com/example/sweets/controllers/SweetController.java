package com.example.sweets.controllers;
import com.example.sweets.models.DB;
import com.example.sweets.models.Sweet;
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

public class SweetController implements Initializable {
    @FXML
    private TableView<Sweet> tableView;
    @FXML
    private TableColumn<Sweet, String> idColumn;
    @FXML
    private TableColumn<Sweet, String> nameColumn;
    @FXML
    private TableColumn<Sweet, String> priceColumn;
    @FXML
    private TableColumn<Sweet, String> amountColumn;
    @FXML
    private TableColumn<Sweet, String> sugarColumn;
    Controller controller = new Controller();

    @FXML
    protected void addSweet(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/Add-View.fxml");
    }
    @FXML
    protected void deleteSweet(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            controller.showMessage("Choose a sweet to delete");
            return;
        }
        DB.sweet = tableView.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/Delete-View.fxml");
    }
    @FXML
    protected void editSweet(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            controller.showMessage("Choose a sweet to edit");
            return;
        }
        DB.sweet = tableView.getSelectionModel().getSelectedItem();
        controller.goTo(event, "src/main/resources/com/example/sweets/Edit-view.fxml");
    }
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/editGift.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.setCells(idColumn, nameColumn, priceColumn, sugarColumn, amountColumn);
        DB db = new DB();
        ObservableList<Sweet>sweets = FXCollections.observableArrayList(db.getSweets(DB.gift.getId()));
        tableView.setItems(sweets);
    }
}

