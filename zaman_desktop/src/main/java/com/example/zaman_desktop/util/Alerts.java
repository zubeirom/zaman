package com.example.zaman_desktop.util;

import javafx.scene.control.Alert;

public class Alerts {

// HOW TO USE;
//    Alerts.error(
//            "Database error",
//            "Could not load database",
//            "Error loading SQLite database. See log. Quitting..."
//            ).showAndWait();
    public static Alert error(String windowTitle, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(windowTitle);
        alert.setHeaderText(header);
        alert.setContentText(description);
        return alert;
    }

    public static Alert info(String windowTitle, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(windowTitle);
        alert.setHeaderText(header);
        alert.setContentText(description);
        return alert;
    }

    public static Alert warning(String windowTitle, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(windowTitle);
        alert.setHeaderText(header);
        alert.setContentText(description);
        return alert;
    }
}