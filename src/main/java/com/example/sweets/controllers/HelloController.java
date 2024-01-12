package com.example.sweets.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class HelloController {
    Controller controller = new Controller();
    @FXML
    protected void viewGift(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/Gift-View.fxml");
    }
    @FXML
    protected void Cancel(ActionEvent event) throws  IOException {
        controller.goTo(event, "Menu.fxml");
    }
}