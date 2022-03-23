package com.example.zaman_desktop.controller;

import com.example.zaman_desktop.IndexApplication;
import com.example.zaman_desktop.model.CreateAppointmentDto;
import com.example.zaman_desktop.model.Appointment;
import com.example.zaman_desktop.model.User;
import com.example.zaman_desktop.service.AppointmentService;
import com.example.zaman_desktop.service.IAppointmentService;
import com.example.zaman_desktop.service.IUserService;
import com.example.zaman_desktop.service.UserService;
import com.example.zaman_desktop.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewAppointmentController implements Initializable {

    @FXML
    public DateTimePicker datePicker_endAt;

    @FXML
    public TextField title_txt;
    public TextField description_txt;
    public TextField location_txt;
    public DateTimePicker datePicker_startAt;
    @FXML
    public MenuButton choices;

    public Button closeButton;

    final List<String> selectedItems = new ArrayList<>();

    IUserService userService;

    IAppointmentService appointmentService;

    List<User> allUsers;

    public NewAppointmentController() {
        this.userService = new UserService();
        this.appointmentService = new AppointmentService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allUsers = this.userService.getAllUsers();
        for (User user : allUsers) {
            CheckMenuItem item = new CheckMenuItem(user.getEmail());
            item.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue) {
                    selectedItems.add(item.getText());
                } else {
                    selectedItems.remove(item.getText());
                }
            });
            choices.getItems().add(item);
        }
    }


    @FXML
    private void cancelButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        openIndexView();
    }


    public void create(ActionEvent actionEvent) {
        if(datePicker_startAt == null) {
            errorAlert();
            return;
        }
        if(datePicker_endAt == null) {
            errorAlert();
            return;
        }
        if(title_txt.getText().isBlank() || title_txt.getText().isEmpty()) {
            errorAlert();
            return;
        }

        Timestamp endsAt = Timestamp.valueOf(datePicker_endAt.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES));
        Timestamp startsAt = Timestamp.valueOf(datePicker_startAt.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES));

        System.out.println(endsAt);
        System.out.println(startsAt);

        Appointment appointment = new Appointment(location_txt.getText(), title_txt.getText(), description_txt.getText(), startsAt, endsAt);

        List<User> selectedUsers = getSelectedUsers();
        CreateAppointmentDto createAppointmentDto = new CreateAppointmentDto(appointment, selectedUsers);
        this.appointmentService.createAppointment(createAppointmentDto);
        Alerts.info("Great Job!", "Your appointment is successfully created", "Check your emails to see details and accept or decline your appointment").showAndWait();
        cancelButton();
    }

    private List<User> getSelectedUsers() {
        List<User> selectedUser = new ArrayList<>();
        for (String email : selectedItems) {
            User user = allUsers.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
            selectedUser.add(user);
        }
        return selectedUser;
    }

    public void errorAlert() {
        Alerts.error("Error", "Something went wrong!", "Make sure to fill out all fields").showAndWait();
    }

    private void openIndexView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("index-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Zaman Terminkalendar");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
