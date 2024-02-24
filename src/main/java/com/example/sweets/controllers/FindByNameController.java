package com.example.sweets.controllers;

import com.example.sweets.models.DB;
import com.example.sweets.models.Gift;
import com.example.sweets.models.Sweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FindByNameController implements Initializable {
    @FXML
    private TableView<Sweet> tableView;
    @FXML
    private TextField Name;


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
        public void goBackClick(ActionEvent event) throws IOException {
            controller.goTo(event, "src/main/resources/com/example/sweets/gift-view.fxml");
        }
        @FXML
        public void onFindClick(ActionEvent event) {
            if (Name.getText().isEmpty()) {
                controller.showMessage("Write a name");
                return;
            }
            Gift gift = DB.gift;
            DB db = new DB();
            gift.setPresent(db.getSweets(DB.gift.getId()));
            tableView.setItems(FXCollections.observableArrayList(gift.findByName(Name.getText())));
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            controller.setCells(idColumn, nameColumn, priceColumn, sugarColumn, amountColumn);
            DB db = new DB();
            ObservableList<Sweet> sweets = FXCollections.observableArrayList(db.getSweets(DB.gift.getId()));
            tableView.setItems(sweets);
        }
    }

