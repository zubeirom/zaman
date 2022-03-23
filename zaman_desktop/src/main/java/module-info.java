module com.example.zaman_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires unirest.java;
    requires org.xerial.sqlitejdbc;
    requires tornadofx.controls;
    requires java.base;


    opens com.example.zaman_desktop to javafx.fxml;
    exports com.example.zaman_desktop;
    exports com.example.zaman_desktop.controller;
    opens com.example.zaman_desktop.controller to javafx.fxml;
    exports com.example.zaman_desktop.model;
    opens com.example.zaman_desktop.model to com.google.gson, javafx.fxml;
}