package com.example.zaman_desktop.fx.models;

import com.example.zaman_desktop.enums.UserTyp;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.security.Timestamp;

public class UserFx {

    private StringProperty firstName;
    private StringProperty  lastName;
    private StringProperty  password;
    private StringProperty  Email;
    private ObjectProperty<UserTyp> type;
    private ObjectProperty<Timestamp> createdAt;
    private ObjectProperty<Timestamp>updateAt;

    public UserFx() {
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        password = new SimpleStringProperty();
        Email = new SimpleStringProperty();
        type=  new SimpleObjectProperty<UserTyp>();
        createdAt=  new SimpleObjectProperty<Timestamp>();
        updateAt=  new SimpleObjectProperty<Timestamp>();

    }


    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public UserTyp getType() {
        return type.get();
    }

    public ObjectProperty<UserTyp> typeProperty() {
        return type;
    }

    public Timestamp getCreatedAt() {
        return createdAt.get();
    }

    public ObjectProperty<Timestamp> createdAtProperty() {
        return createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt.get();
    }

    public ObjectProperty<Timestamp> updateAtProperty() {
        return updateAt;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public void setType(UserTyp type) {
        this.type.set(type);
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt.set(createdAt);
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt.set(updateAt);
    }
}
