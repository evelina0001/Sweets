package com.example.sweets.controllers;
import com.example.sweets.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSweetController implements Initializable {
    @FXML
    private Label ErrLabel;
    @FXML
    private TextField input;
    @FXML
    Controller controller = new Controller();

    @FXML
    protected void onCancelClick(ActionEvent event) throws IOException {
        controller.goTo(event, "src/main/resources/com/example/sweets/sweet-view.fxml");
    }
    @FXML
    protected void onEditClick(ActionEvent event) throws Exception {
        if (controller.isNumeric(input.getText())) {  //Перевіряє чи є числом
          ErrLabel.setText(" ");
          if (Double.parseDouble(input.getText()) <= 0 ) {
              ErrLabel.setText(" Input cant be negative number");
              return;
          }
        } else {
            ErrLabel.setText(" !Input should be a number! ");
            return;
        }

    if(DB.sweet instanceof Candy){
        double inp = Double.parseDouble(input.getText());
        Candy candy = (Candy)DB.sweet;
        candy.setWeight(inp);
        DB db = new DB();
        db.editCandy(candy);
    }
    else {
        int inp = Integer.parseInt(input.getText());
        Biscuit biscuit = (Biscuit)DB.sweet;
        biscuit.setAmount(inp);
        DB db = new DB();
        db.editBiscuit(biscuit);
        }
    input.clear();
        controller.goTo(event, "src/main/resources/com/example/sweets/sweet-view.fxml");
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
  if (DB.sweet instanceof Candy) { //Первіряємо тип данних
      Candy candy = (Candy)DB.sweet;
      input.setText(candy.getWeight() + ""); //Перетворюємо в String
  } else {
      Biscuit biscuit = (Biscuit)DB.sweet;
      input.setText(biscuit.getAmount() + "");
  }
    }
}
