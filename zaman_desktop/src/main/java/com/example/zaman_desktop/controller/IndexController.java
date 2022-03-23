package com.example.zaman_desktop.controller;

import com.example.zaman_desktop.IndexApplication;
import com.example.zaman_desktop.model.*;
import com.example.zaman_desktop.service.AppointmentService;
import com.example.zaman_desktop.service.IAppointmentService;
import com.example.zaman_desktop.util.Util;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class IndexController implements Initializable {

    public TextField navSearch;
    public Label selectedAppointmentParticipants;
    public Label selectedAppointmentTo;
    public Label selectedAppointmentFrom;
    public Label selectedAppointmentLocation;
    public Label selectedAppointmentTitle;
    public Label selectedAppointmentNote;
    public VBox buttonBar;

    @FXML
    private Label welcomeText;

    @FXML
    private DatePicker txtFrom;

    @FXML
    private DatePicker txtTo;

    @FXML
    private Button btnNewAppointment;

    @FXML
    private Button btnDecline;

    @FXML
    private Button btnAccept;

    @FXML
    private TableView appointmentTable;

    private IAppointmentService appointmentService;

    public IndexController() {
        this.appointmentService = new AppointmentService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    void acceptClicked(ActionEvent event) {
    }

    @FXML
    void declineClicked(ActionEvent event) {
    }

    @FXML
    void newAppointmentClicked(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("new-Appointment-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Zaman new Appointment");
            stage.setScene(scene);
            stage.show();

            closeScene();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    void setSelectedAppointment(AppointmentTable appointmentTable) {
        selectedAppointmentTitle.setText(appointmentTable.getTitle());
        selectedAppointmentLocation.setText("Location: " + appointmentTable.getLocation());
        selectedAppointmentFrom.setText("From: " + appointmentTable.getBegins());
        selectedAppointmentTo.setText("To: " + appointmentTable.getEnds());
        selectedAppointmentNote.setText(appointmentTable.getNote());
    }

    private void closeScene() {
        Stage logInStage = (Stage) btnNewAppointment.getScene().getWindow();
        logInStage.close();
    }

    public void logout(ActionEvent actionEvent) {
        try {
            Util.setToken("null");
            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("user-login-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage backLoginStage = new Stage();
            backLoginStage.setScene(new Scene(root));
            backLoginStage.setTitle("Zaman Login");
            backLoginStage.show();
            closeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(ActionEvent actionEvent) {
        init();
    }

    public void init() {
        appointmentTable.getItems().clear();
        List<Participant> participants = appointmentService.getAppointments();

        for (Participant participant : participants) {
            System.out.println(participant.getAppointment().getTitle());
        }

        List<AppointmentTable> data = new ArrayList<AppointmentTable>();

        for (Participant participant : participants) {
            data.add(
                    new AppointmentTable(participant.getAppointment().getTitle(), participant.getStatus(), participant.getAppointment().getStartsAt().toString(), participant.getAppointment().getStartsAt().toString(), participant.getAppointment().getEndsAt().toString(), participant.getAppointment().getLocation(), "", participant.getAppointment().getDescription())
            );
        }

        TableColumn<AppointmentTable, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTable, String>("title"));

        TableColumn<AppointmentTable, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTable, String>("status"));

        TableColumn<AppointmentTable, String> beginsColumn = new TableColumn<>("Begins");
        beginsColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTable, String>("fromNow"));

        appointmentTable.getColumns().add(titleColumn);
        appointmentTable.getColumns().add(beginsColumn);
        appointmentTable.getColumns().add(statusColumn);

        titleColumn.prefWidthProperty().setValue(354.0);
        beginsColumn.prefWidthProperty().setValue(147.0);
        statusColumn.prefWidthProperty().setValue(101.0);

        appointmentTable.getItems().addAll(data);

        if(!data.isEmpty())
            setSelectedAppointment(data.get(0));

        appointmentTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AppointmentTable>() {
            @Override
            public void changed(ObservableValue<? extends AppointmentTable> observableValue, AppointmentTable oldValue, AppointmentTable newValue) {
                setSelectedAppointment(newValue);
            }
        });
    }
}
