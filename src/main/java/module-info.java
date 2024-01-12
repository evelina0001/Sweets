module com.example.sweets {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.sweets to javafx.fxml;
    exports com.example.sweets;
    exports com.example.sweets.controllers;
    opens com.example.sweets.controllers to javafx.fxml;
}