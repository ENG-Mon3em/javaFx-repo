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

import java.io.File;
import java.util.Objects;

public class SignIn {

    // declaration the controls
    Label emailLabel;
    Label passwordLabel;
    Label spacerLabel;
    TextField emailTextField;
    PasswordField passwordField;
    Button signInButton;
    Button signUpButton;
    String imagePath;
    Image libraryImage;
    ImageView imageView;
    GridPane rootGridPane;
    VBox buttonVBox;
    Scene signInScene;

    //SignIn Constructor
    SignIn() {
        initControls();
        renderControls();
    }

    // initialization the Controls
    void initControls() {
        emailLabel = new Label("Email");
        emailTextField = new TextField();
        spacerLabel = new Label();
        passwordLabel = new Label("Password");
        passwordField = new PasswordField();
        signInButton = new Button("Sign In");
        signInButton.setPrefWidth(200);
        signUpButton = new Button("Sign Up");
        signUpButton.setPrefWidth(200);
        buttonVBox = new VBox(10, signInButton, signUpButton);
        buttonVBox.setAlignment(Pos.CENTER);
        rootGridPane = new GridPane();
        rootGridPane.setAlignment(Pos.CENTER);
        rootGridPane.setHgap(10);
        rootGridPane.setVgap(10);
        imagePath = new File("C:\\Repos\\javaFx-repo\\src\\main\\resources\\Images\\library.png").toURI().toString();
        libraryImage = new Image(imagePath);
        imageView = new ImageView(libraryImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    // render VBox , HBox & FlowPane
    void renderControls() {
        rootGridPane.add(imageView, 0, 0, 2, 1);
        rootGridPane.add(emailLabel, 0, 2);
        rootGridPane.add(passwordLabel, 0, 3);
        rootGridPane.add(emailTextField, 1, 2);
        rootGridPane.add(passwordField, 1, 3);
        rootGridPane.add(spacerLabel, 0, 4);
        rootGridPane.add(buttonVBox, 0, 5, 2, 1);
    }

    public Scene getScene() {
        signInScene = new Scene(rootGridPane, 650, 720);
        signInScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return signInScene;
    }
}