package com.example.sweets.controllers;
import com.example.sweets.models.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSweetController implements Initializable {
    @FXML
    TableView<Sweet> tableView;
    @FXML
    private TableColumn<Sweet, String> name;
    @FXML
    private TableColumn<Sweet, String> price;
    @FXML
    TableColumn<Sweet, String >id;
    @FXML
    private TableColumn<Sweet, String> sugar;

    Controller controller = new Controller();
    @FXML
    private TextField input;
    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/sweet-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DB db = new DB();
        ObservableList<Sweet> allSweets = FXCollections.observableArrayList(db.getAllSweet());
        //солодощі з бази даних отримуються за допомогою db.getAllSweet(),
        // і потім конвертуються в ObservableList типу Sweet. ObservableList
        setCells(id, name, price, sugar);
        tableView.setItems(allSweets);
    }
    private void setCells(TableColumn<Sweet, String> id, TableColumn<Sweet, String> name, TableColumn<Sweet, String> price, TableColumn<Sweet, String> sugar) {
        //Встановлюємо рядки
        name.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getName()));
        id.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getId() + " "));
        price.setCellValueFactory(cellData->new ReadOnlyStringWrapper(String.format("%.2f", cellData.getValue().getPrice())));
        sugar.setCellValueFactory(cellData->new ReadOnlyStringWrapper(String.format("%.2f", cellData.getValue().getSugar())));

    }
    @FXML
    protected void onAddClick() throws  CloneNotSupportedException {
        if (controller.isNumeric(input.getText())) { //Перевіряємо чи введена інформація є числом
            if (Double.parseDouble(input.getText()) <= 0 ) {
                controller.showMessage( " Input cant be negative number ");
                return;
            }
        } else {
            controller.showMessage("Input should be a number");
            return;
        }
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index == - 1) {
            controller.showMessage("Choose a sweet");
            return;
        }
        DB db = new DB();
            Sweet s = (Sweet) tableView.getSelectionModel().getSelectedItem().clone();
            //Отримуємо глибоку копію обєкта
            System.out.println(s);
        if(s.getClass().getSimpleName().equals("Candy")) {
            //Перевіряємо чи наша цукерка належеть в Candy
            double w = Double.parseDouble(input.getText());
            Candy candy = (Candy)s;
            candy.setWeight(w);
            db.addCandy(DB.gift.getId(), candy);
        }
        else {

            int a = Integer.parseInt(input.getText());
            Biscuit biscuit = (Biscuit)s;
            biscuit.setAmount(a);
            db.addBiscuit(DB.gift.getId(), biscuit);
        }
        input.clear();
    }
}
