package com.example.sweets.controllers;
import com.example.sweets.models.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class DeleteGiftController {
    Controller controller = new Controller();

    @FXML
    protected void onGoBackClick(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/gift-view.fxml");
    }
    @FXML
    protected void onDeleteClick(ActionEvent event) throws IOException {
        DB db = new DB();
        db.deleteGift(DB.gift);
        controller.goTo(event, "src/main/resources/com/example/sweets/gift-view.fxml");
    }


}