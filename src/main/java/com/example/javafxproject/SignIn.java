package com.example.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SignIn {
    public Scene getScene() {

        Label emailLabel;
        Label passwordLabel;
        Label spacerLabel;
        TextField emailTextField;
        PasswordField passwordField;
        Button signInButton;
        Button signUpButton;
        Image libraryImage;
        ImageView imageView;
        GridPane rootGridPane;
        VBox buttonVBox;
        Scene signInScene;


        emailLabel = new Label("Email");
        passwordLabel = new Label("Password");
        spacerLabel = new Label();
        emailTextField = new TextField();
        passwordField = new PasswordField();
        signInButton = new Button("Sign In");
        signUpButton = new Button("Sign Up");
        rootGridPane = new GridPane();


        signInButton.setPrefWidth(200);
        signUpButton.setPrefWidth(200);


        libraryImage = new Image(Objects.requireNonNull(getClass().getResource("/Images/Library.jpg")).toString());
        imageView = new ImageView(libraryImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);


        buttonVBox = new VBox(10, signInButton, signUpButton);
        rootGridPane.add(imageView, 0, 0, 2, 1);
        rootGridPane.add(emailLabel, 0, 2);
        rootGridPane.add(passwordLabel, 0, 3);
        rootGridPane.add(emailTextField, 1, 2);
        rootGridPane.add(passwordField, 1, 3);
        rootGridPane.add(spacerLabel, 0, 4);
        rootGridPane.add(buttonVBox, 0, 5, 2, 1);
        rootGridPane.setHgap(10);
        rootGridPane.setVgap(10);
        rootGridPane.setAlignment(Pos.CENTER);
        buttonVBox.setAlignment(Pos.CENTER);


        signInScene = new Scene(rootGridPane, 400, 600);
        signInScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return signInScene;
    }
}