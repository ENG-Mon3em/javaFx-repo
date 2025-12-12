package com.example.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class SignUp {
    public Scene getScene() {


        GridPane rootGridPane;
        Label nameLabel;
        TextField nameTextField;
        Label emailLabel;
        TextField emailTextField;
        Label passwordLabel;
        PasswordField passwordTextField;
        Label confirmPasswordLabel;
        PasswordField confirmPasswordField;
        Button signUpButton;
        ToggleGroup userTypeToggleGroup;
        RadioButton userRadioButton;
        RadioButton adminRadioButton;
        Scene signUpScene;


        rootGridPane = new GridPane();
        nameLabel = new Label("Name:");
        nameTextField = new TextField();
        emailLabel = new Label("Email:");
        emailTextField = new TextField();
        passwordLabel = new Label("Password:");
        passwordTextField = new PasswordField();
        confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordField = new PasswordField();
        signUpButton = new Button("Sign Up");
        userTypeToggleGroup = new ToggleGroup();
        userRadioButton = new RadioButton("user");
        adminRadioButton = new RadioButton("admin");


        userTypeToggleGroup.getToggles().addAll(userRadioButton, adminRadioButton);


        rootGridPane.add(nameLabel, 0, 0);
        rootGridPane.add(nameTextField, 1, 0);
        rootGridPane.add(emailLabel, 0, 1);
        rootGridPane.add(emailTextField, 1, 1);
        rootGridPane.add(passwordLabel, 0, 2);
        rootGridPane.add(passwordTextField, 1, 2);
        rootGridPane.add(confirmPasswordLabel, 0, 3);
        rootGridPane.add(confirmPasswordField, 1, 3);
        rootGridPane.add(userRadioButton, 0, 4);
        rootGridPane.add(adminRadioButton, 1, 4);
        rootGridPane.add(signUpButton, 1, 5);


        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.setAlignment(Pos.CENTER);


        signUpScene = new Scene(rootGridPane, 500, 600);
        signUpScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return signUpScene;
    }
}