package com.example.javafxproject;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class viewBooks {

    Label titleLabel;
    TextArea DetailsTextArea;
    TableView<BooksData> table;
    TableColumn<BooksData, Integer> id;
    TableColumn<BooksData, String> bookName;
    TableColumn<BooksData, String> authorName;
    Button storeButton;
    Button backButton;
    VBox vBoxdetails;
    VBox vBoxButtons;
    VBox vBox;
    VBox firstPart;
    HBox secondPart;
    FlowPane flowPane;
    Scene viewBooksScene;

    viewBooks() {
        initControls();
        initTable();
        renderControls();
    }

    void initControls() {
        titleLabel = new Label("View");
        DetailsTextArea = new TextArea();
        DetailsTextArea.setPromptText("Book Details");
        storeButton = new Button("Store");
        storeButton.setId("DownloadButton");
        backButton = new Button("Back");
        backButton.setId("backButton");
    }

    void initTable() {
        table = new TableView<>();

        id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        bookName = new TableColumn<>("BookName");
        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));

        authorName = new TableColumn<>("Author");
        authorName.setCellValueFactory(new PropertyValueFactory<>("Author"));

        table.getColumns().addAll(id, bookName, authorName);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(800);
    }

    void renderControls() {
        firstPart = new VBox(titleLabel, table);
        firstPart.setAlignment(Pos.CENTER);
        firstPart.setSpacing(20);

        vBoxButtons = new VBox(storeButton, backButton);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(20);

        secondPart = new HBox(DetailsTextArea, vBoxButtons);
        secondPart.setSpacing(10);
        secondPart.setAlignment(Pos.CENTER);

        vBox = new VBox(firstPart, secondPart);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        flowPane = new FlowPane(vBox);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(20));
    }

    Scene getScene() {
        if (viewBooksScene == null) {
            viewBooksScene = new Scene(flowPane, 1500, 800);
            viewBooksScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return viewBooksScene;
    }
}