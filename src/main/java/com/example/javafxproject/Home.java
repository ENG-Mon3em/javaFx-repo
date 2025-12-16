package com.example.javafxproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Objects;

public class Home {

    Label welcomeLabel;
    Button editUserButton;
    Button editBookButton;
    Button shopButton;
    Button infoButton;
    Button logOutButton;
    String imagePath;
    Image libraryImage;
    ImageView imageView;
    VBox mainVBox;
    Scene homeScene;

    public Home() {
        initControls();
        renderControls();
    }

    void initControls() {
        welcomeLabel = new Label("Welcome to The Library System");
        editUserButton = new Button("Edit User");
        editBookButton = new Button("Edit Book");
        shopButton = new Button("Shop");
        infoButton = new Button("Info");
        logOutButton = new Button("LogOut");

        imagePath = new File("C:\\Repos\\javaFx-repo\\src\\main\\resources\\Images\\library.png").toURI().toString();
        libraryImage = new Image(imagePath);
        imageView = new ImageView(libraryImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    void renderControls() {
        HBox hBoxBooksButtons = new HBox(infoButton, shopButton, editUserButton, editBookButton, logOutButton);
        hBoxBooksButtons.setAlignment(Pos.CENTER);
        hBoxBooksButtons.setSpacing(20);
        mainVBox = new VBox(imageView, welcomeLabel, hBoxBooksButtons);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(20);
        mainVBox.setPadding(new Insets(20));
    }

    public Scene getHomeScene() {
        if (homeScene == null) {
            homeScene = new Scene(mainVBox, 1500, 800);
            homeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return homeScene;
    }
}
