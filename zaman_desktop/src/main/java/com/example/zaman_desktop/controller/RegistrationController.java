package com.example.zaman_desktop.controller;

import com.example.zaman_desktop.IndexApplication;
import com.example.zaman_desktop.model.User;
import com.example.zaman_desktop.service.IUserService;
import com.example.zaman_desktop.service.UserService;
import com.example.zaman_desktop.util.Alerts;
import com.example.zaman_desktop.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistrationController implements Initializable {
    public Button btnSignup;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView imageLogoView;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtConfirmPassword;

    private IUserService userService;

    public RegistrationController() {
        this.userService = new UserService();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userService = new UserService();
        File logoFile = new File("zaman_desktop/src/main/resources/com/example/zaman_desktop/Logo.png");
        Image imageLogo = new Image(logoFile.toURI().toString());
        imageLogoView.setImage(imageLogo);
    }

    public void backToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("user-login-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage backLoginStage = new Stage();
            backLoginStage.setScene(new Scene(root));
            backLoginStage.setTitle("Zaman Login");
            backLoginStage.show();
            closeScene();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void signUp(ActionEvent actionEvent) {
        try {
            String firstName = txtFirstname.getText();
            String lastName = txtLastname.getText();
            String password = txtPassword.getText();
            String email = txtEmail.getText();
            String confirmPassword = txtConfirmPassword.getText();

            if((password.isEmpty() || password.isBlank()) || !password.equals(confirmPassword)) {
                Alerts.error("Error", "Password do not match", "Please try again!").showAndWait();
            } else if(!emailIsValid(email)) {
                Alerts.error("Error", "Email is not valid", "Please try again!").showAndWait();
            } else if(firstName == null || firstName.isEmpty() || firstName.isBlank()) {
                Alerts.error("Error", "Firstname missing", "Please try again").showAndWait();
            } else if(lastName == null || lastName.isEmpty() || lastName.isBlank()) {
                Alerts.error("Error", "Lastname missing", "Please try again!").showAndWait();
            } else {
                User newUser = new User(firstName, lastName, email, password);
                userService.createUser(newUser);
                Alerts.info("Information", "Thank you for signing in!", "Please check your inbox, to verify your email, make sure to checkout your spam if mail is missing").showAndWait();
                backToLogin(actionEvent);
            }


//            FXMLLoader fxmlLoader = new FXMLLoader(IndexApplication.class.getResource("index-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//            Stage stage = new Stage();w
//            stage.setTitle("Zaman Terminkalendar");
//            stage.setScene(scene);
//            stage.show();

//            closeScene();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean emailIsValid(String email) {
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches();
    }

    private void closeScene() {
        Stage signupStage = (Stage) btnBack.getScene().getWindow();
        signupStage.close();
    }
}
