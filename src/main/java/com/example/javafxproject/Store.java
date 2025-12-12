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

    public Scene getScene() {

        Label titleLabel;
        TextField searchTextField;
        TextArea DetailsTextArea;
        TableView table;
        Button okButton, downloadButton, backButton;
        VBox vBoxdetails, vBoxButtons, vBox, firstPart;
        HBox hBoxSearch, secondPart;
        FlowPane flowPane;
        Scene storeScene;

        //first part of page
        //Table

        table = new TableView();
        TableColumn<Books, Integer> id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Books, String> bookName = new TableColumn("BookName");
        bookName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Books, String> authorName = new TableColumn("Author");
        authorName.setCellValueFactory(new PropertyValueFactory("Author"));
//        TableColumn<Books, String> bookDetails = new TableColumn("Details");
//        bookDetails.setCellValueFactory(new PropertyValueFactory("Details"));
        table.getColumns().addAll(id, bookName, authorName);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //second part of page
        //details & download
        titleLabel = new Label("Store");
        DetailsTextArea = new TextArea();
        DetailsTextArea.setPromptText("Book Details");
        searchTextField = new TextField();
        searchTextField.setPromptText("Enter Book Id");
        downloadButton = new Button("Download");
        downloadButton.setId("DownloadButton");
        backButton = new Button("Back");
        backButton.setId("backButton");
        okButton = new Button("ok");

        firstPart = new VBox(titleLabel, table);
        firstPart.setAlignment(Pos.CENTER);

        hBoxSearch = new HBox(searchTextField, okButton);
        hBoxSearch.setSpacing(5);
        hBoxSearch.setAlignment(Pos.CENTER);

        vBoxdetails = new VBox(hBoxSearch, DetailsTextArea);
        vBoxdetails.setAlignment(Pos.CENTER);
        vBoxdetails.setSpacing(5);

        vBoxButtons = new VBox(downloadButton, backButton);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(20);

        secondPart = new HBox(vBoxdetails, vBoxButtons);


        vBox = new VBox(firstPart, secondPart);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        flowPane = new FlowPane(vBox);
        flowPane.setAlignment(Pos.CENTER);
        storeScene = new Scene(flowPane, 650, 720);
        storeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return storeScene;
    }
}
