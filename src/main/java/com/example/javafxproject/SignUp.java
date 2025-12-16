package com.example.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class SignUp {

    GridPane rootGridPane;
    Label nameLabel;
    TextField nameTextField;
    Label idLabel;
    TextField idTextfield;
    Label passwordLabel;
    PasswordField passwordTextField;
    Label confirmPasswordLabel;
    PasswordField confirmPasswordField;
    Button signUpButton;
    Scene signUpScene;

    public SignUp() {
        initControls();
        renderControls();
    }

    void initControls() {
        rootGridPane = new GridPane();

        nameLabel = new Label("Name:");
        nameTextField = new TextField();

        idLabel = new Label("Id:");
        idTextfield = new TextField();

        passwordLabel = new Label("Password:");
        passwordTextField = new PasswordField();

        confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordField = new PasswordField();

        signUpButton = new Button("Sign Up");
        signUpButton.setId("DownloadButton");

        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.setAlignment(Pos.CENTER);
    }

    void renderControls() {
        rootGridPane.add(idLabel, 0, 0);
        rootGridPane.add(idTextfield, 1, 0);

        rootGridPane.add(nameLabel, 0, 1);
        rootGridPane.add(nameTextField, 1, 1);

        rootGridPane.add(passwordLabel, 0, 2);
        rootGridPane.add(passwordTextField, 1, 2);

        rootGridPane.add(confirmPasswordLabel, 0, 3);
        rootGridPane.add(confirmPasswordField, 1, 3);

        rootGridPane.add(signUpButton, 1, 4, 2, 1);
    }

    public Scene getScene() {
        if (signUpScene == null) {
            signUpScene = new Scene(rootGridPane, 1500, 800);
            signUpScene.getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/style.css")
                    ).toExternalForm()
            );
        }
        return signUpScene;
    }
}
