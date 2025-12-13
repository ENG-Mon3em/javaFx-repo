package com.example.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class SignUp {
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
    HBox radioButtonsHBox;

    public SignUp() {
        initControls();
        renderControls();
    }

    void initControls() {
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
        signUpButton.setId("DownloadButton");
        userTypeToggleGroup = new ToggleGroup();
        userRadioButton = new RadioButton("user");
        adminRadioButton = new RadioButton("admin");

        userTypeToggleGroup.getToggles().addAll(userRadioButton, adminRadioButton);

        radioButtonsHBox = new HBox(20, userRadioButton, adminRadioButton);
        radioButtonsHBox.setAlignment(Pos.CENTER_LEFT);

        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.setAlignment(Pos.CENTER);
    }

    void renderControls() {
        rootGridPane.add(nameLabel, 0, 0);
        rootGridPane.add(nameTextField, 1, 0);
        rootGridPane.add(emailLabel, 0, 1);
        rootGridPane.add(emailTextField, 1, 1);
        rootGridPane.add(passwordLabel, 0, 2);
        rootGridPane.add(passwordTextField, 1, 2);
        rootGridPane.add(confirmPasswordLabel, 0, 3);
        rootGridPane.add(confirmPasswordField, 1, 3);
        rootGridPane.add(radioButtonsHBox, 1, 4);
        rootGridPane.add(signUpButton, 1, 5, 2, 1);
    }

    public Scene getScene() {
        if (signUpScene == null) {
            signUpScene = new Scene(rootGridPane,1500,800);
            signUpScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return signUpScene;
    }
}