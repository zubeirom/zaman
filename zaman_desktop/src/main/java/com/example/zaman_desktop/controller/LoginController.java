package com.example.zaman_desktop.controller;
import com.example.zaman_desktop.IndexApplication;
import com.example.zaman_desktop.service.AppointmentService;
import com.example.zaman_desktop.service.IAppointmentService;
import com.example.zaman_desktop.service.IUserService;
import com.example.zaman_desktop.service.UserService;
import com.example.zaman_desktop.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnSignUp;
    @FXML
    private ImageView imageLogoView;

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private IUserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logoFile = new File("zaman_desktop/src/main/resources/com/example/zaman_desktop/Logo.png");
        Image imageLogo = new Image(logoFile.toURI().toString());
        imageLogoView.setImage(imageLogo);
    }

    public void clickSignUp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("registration-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage registrationStage = new Stage();
            registrationStage.setScene(new Scene(root));
            registrationStage.setTitle("Zaman Registration");
            registrationStage.show();

            closeScene();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void logIn(ActionEvent event) throws Exception {
        try {
            if(txtUsername.getText().isEmpty() || txtUsername.getText().isBlank()) {
                Alerts.error("Error", "Something went wrong","Make sure to fill out fields").showAndWait();
                return;
            }

            if(txtPassword.getText().isEmpty() || txtUsername.getText().isBlank()) {
                Alerts.error("Error", "Something went wrong", "Make sure to fill out fields").showAndWait();
                return;
            }

            if(!this.userService.loginUser(txtUsername.getText(), txtPassword.getText())) {
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("index-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Zaman Terminkalendar");
            stage.setScene(scene);
            stage.show();

            closeScene();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeScene() {
        Stage logInStage = (Stage) btnSignin.getScene().getWindow();
        logInStage.close();
    }

}
