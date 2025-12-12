package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Objects;

public class Home {

    // declaration the controls
    GridPane rootGridPane;
    Label welcomeLabel;
    Button editUserButton;
    Button editBookButton;
    Button shopButton;
    Button infoButton;
    TableView<BookPre> table;
    TableColumn<BookPre, String> nameColumn;
    TableColumn<BookPre, String> idColumn;
    String imagePath;
    Image libraryImage;
    ImageView imageView;
    VBox mainVBox;
    Scene homeScene;

    // Home Constructor
    public Home() {
        initControls();
        renderControls();
    }

    // initialization the Controls
    void initControls() {
        rootGridPane = new GridPane();
        rootGridPane.setHgap(15);
        rootGridPane.setVgap(15);
        rootGridPane.setAlignment(Pos.CENTER);
        rootGridPane.setPadding(new Insets(20));

        welcomeLabel = new Label("Welcome to The Library System");
        editUserButton = new Button("Edit User");
        editBookButton = new Button("Edit Book");
        shopButton = new Button("Shop");
        infoButton = new Button("Info");

        table = new TableView<>();
        nameColumn = new TableColumn<>("Name");
        idColumn = new TableColumn<>("Id");

        idColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().addAll(nameColumn, idColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxWidth(500);
        table.setMaxHeight(300);

        table.setItems(FXCollections.observableArrayList(
                new BookPre("Java Programming", "B001"),
                new BookPre("Data Structures", "B002"),
                new BookPre("Algorithms", "B003"),
                new BookPre("Operating Systems", "B004"),
                new BookPre("Database Systems", "B005"),
                new BookPre("Computer Networks", "B006"),
                new BookPre("Artificial Intelligence", "B007"),
                new BookPre("Machine Learning", "B008"),
                new BookPre("Web Development", "B009"),
                new BookPre("Software Engineering", "B010")
        ));

        imagePath = new File("C:\\Repos\\javaFx-repo\\src\\main\\resources\\Images\\library.png").toURI().toString();
        libraryImage = new Image(imagePath);
        imageView = new ImageView(libraryImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    // render VBox , HBox & FlowPane
    void renderControls() {
        GridPane.setColumnSpan(welcomeLabel, 10);
        rootGridPane.add(welcomeLabel, 0, 0);
        rootGridPane.add(infoButton, 0, 1);
        rootGridPane.add(shopButton, 1, 1);
        rootGridPane.add(editUserButton, 2, 1);
        rootGridPane.add(editBookButton, 3, 1);

        mainVBox = new VBox(imageView,rootGridPane, table);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.CENTER);
    }

    public Scene getHomeScene() {
        homeScene = new Scene(mainVBox);
        homeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return homeScene;
    }
}