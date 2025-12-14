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
    //    TableView<BooksData> table;
//    TableColumn<BooksData, String> name;
//    TableColumn<BooksData, String> authorName;
    String imagePath;
    Image libraryImage;
    ImageView imageView;
    VBox mainVBox;
    Scene homeScene;

    public Home() {
        initControls();
        renderControls();
        initActions();
    }

    void initControls() {

        welcomeLabel = new Label("Welcome to The Library System");
        editUserButton = new Button("Edit User");
        editBookButton = new Button("Edit Book");
        shopButton = new Button("Shop");
        infoButton = new Button("Info");
        logOutButton = new Button("LogOut");

//        table = new TableView<>();
//        name = new TableColumn<>("Name");
//        authorName = new TableColumn<>("authorName");
//
//
//        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
//        table.getColumns().addAll(name, authorName);
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        table.setMaxWidth(500);
//        table.setMaxHeight(300);

        imagePath = new File("C:\\Repos\\javaFx-repo\\src\\main\\resources\\Images\\library.png").toURI().toString();
        libraryImage = new Image(imagePath);
        imageView = new ImageView(libraryImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    void renderControls() {
        HBox hBoxBooksButtons = new HBox(infoButton, shopButton, editUserButton, editBookButton,logOutButton);
        hBoxBooksButtons.setAlignment(Pos.CENTER);
        hBoxBooksButtons.setSpacing(20);
        mainVBox = new VBox(imageView, welcomeLabel, hBoxBooksButtons /*,table*/);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(20);
        mainVBox.setPadding(new Insets(20));
    }

    void initActions() {
        infoButton.setOnAction(event -> {
            ViewBooks viewBooks = new ViewBooks();
            Main.mainStage.setScene(viewBooks.getScene());
        });

        shopButton.setOnAction(event -> {
            Store store = new Store();
            Main.mainStage.setScene(store.getScene());
        });

        editUserButton.setOnAction(event -> {
            AdminUsers adminUsers = new AdminUsers();
            Main.mainStage.setScene(adminUsers.getScene());
        });

        editBookButton.setOnAction(event -> {
            AdminBooks adminBooks = new AdminBooks();
            Main.mainStage.setScene(adminBooks.getScene());
        });

        logOutButton.setOnAction(event -> {
            SignIn signIn = new SignIn();
            Main.mainStage.setScene(signIn.getScene());
        });
    }

    public Scene getHomeScene() {
        if (homeScene == null) {
            homeScene = new Scene(mainVBox,1500,800);
            homeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return homeScene;
    }
}