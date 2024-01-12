package com.example.sweets.controllers;
import com.example.sweets.models.Candy;
import com.example.sweets.models.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class DeleteViewController {
    Controller controller = new Controller();
    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/Edit-view.fxml");
    }

    @FXML
    protected void onDeleteClick(ActionEvent event) throws IOException {
        DB db = new DB();
       if (DB.sweet instanceof Candy) {
           db.deleteCandy(DB.gift.getId(), DB.sweet.getId());
       } else {
           db.deleteBiscuit(DB.gift.getId(), DB.sweet.getId());
       }
        controller.goTo(event, "src/main/resources/com/example/sweets/sweet-view.fxml");

    }
}

