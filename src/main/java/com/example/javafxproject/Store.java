package com.example.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Store {

    Label titleLabel;
    TextArea DetailsTextArea;
    TableView<BooksData> table;
    Button okButton;
    Button downloadButton;
    Button backButton;
    VBox vBoxdetails;
    VBox vBoxButtons;
    VBox vBox;
    VBox firstPart;
    HBox hBoxSearch;
    HBox secondPart;
    FlowPane flowPane;
    Scene storeScene;
    TableColumn<BooksData, Integer> id;
    TableColumn<BooksData, String> bookName;
    TableColumn<BooksData, String> authorName;
    TableColumn<BooksData, String> edition;
    TableColumn<BooksData, String> Storage;

    public Store() {
        initControls();
        renderControls();
    }

    void initControls() {
        table = new TableView<>();
        id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        bookName = new TableColumn("BookName");
        bookName.setCellValueFactory(new PropertyValueFactory("name"));
        authorName = new TableColumn("Author");
        authorName.setCellValueFactory(new PropertyValueFactory("Author"));
        edition = new TableColumn("edition");
        edition.setCellValueFactory(new PropertyValueFactory("edition"));
        Storage = new TableColumn("Storage");
        Storage.setCellValueFactory(new PropertyValueFactory("Storage"));
        table.getColumns().addAll(id, bookName, authorName, edition, Storage);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(800);

        titleLabel = new Label("Store");
        DetailsTextArea = new TextArea();
        DetailsTextArea.setPromptText("Book Details");
        downloadButton = new Button("Download");
        backButton = new Button("Back");
        backButton.setId("backButton");
        okButton = new Button("ok");
    }

    void renderControls() {
        firstPart = new VBox(titleLabel, table);
        firstPart.setAlignment(Pos.CENTER);

        vBoxButtons = new VBox(downloadButton, backButton);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(20);

        vBox = new VBox(firstPart, vBoxButtons);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        flowPane = new FlowPane(vBox);
        flowPane.setAlignment(Pos.CENTER);
    }

    public Scene getScene() {
        if (storeScene == null) {
            storeScene = new Scene(flowPane,1500,800);
            storeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return storeScene;
    }
}