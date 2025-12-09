package com.example.javafxproject;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Sign_up {
    public void show(Stage stage) {
        GridPane root = new GridPane();
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordTextField = new PasswordField();
        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordTextField = new PasswordField();
        Button signUpButton = new Button("Sign Up");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton userRadioButton = new RadioButton("user");
        RadioButton adminRadioButton = new RadioButton("admin");
        genderGroup.getToggles().addAll(userRadioButton, adminRadioButton);
        root.add(nameLabel, 0, 0);
        root.add(nameTextField, 1, 0);
        root.add(emailLabel, 0, 1);
        root.add(emailTextField, 1, 1);
        root.add(passwordLabel, 0, 2);
        root.add(passwordTextField, 1, 2);
        root.add(confirmPasswordLabel, 0, 3);
        root.add(confirmPasswordTextField, 1, 3);
        root.add(userRadioButton, 0, 4);
        root.add(adminRadioButton, 1, 4);
        root.add(signUpButton, 1, 5);
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 600);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }
}
