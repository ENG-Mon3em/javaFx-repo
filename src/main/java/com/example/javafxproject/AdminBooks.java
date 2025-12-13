package com.example.javafxproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class AdminBooks {

    Label titleLabel;
    Label bookIdLabel;
    Label bookTitleLabel;
    Label authorLabel;
    Label pubYearLabel;
    Label priceLabel;
    TextField tBookID;
    TextField tTitle;
    TextField tAuthor;
    TextField tPubYear;
    TextField tPrice;
    TextField searchField;
    Button add;
    Button update;
    Button delete;
    Button refresh;
    Button search;
    Button clear;
    Button back;
    Button users;
    GridPane grid;
    HBox row1;
    HBox row2;
    HBox row3;
    TableView<BooksData> table;
    VBox vbox;
    HBox rootHBox;
    Scene adminScene;


    public AdminBooks() {
        initControls();
        renderControls();
    }

    void initControls() {
        titleLabel = new Label("Book Information");
        bookIdLabel = new Label("ID:");
        bookTitleLabel = new Label("BookName:");
        authorLabel = new Label("AuthorName:");
        pubYearLabel = new Label("Edition:");
        priceLabel = new Label("Storage:");

        tBookID = new TextField();
        tTitle = new TextField();
        tAuthor = new TextField();
        tPubYear = new TextField();
        tPrice = new TextField();
        searchField = new TextField();
        searchField.setPromptText("Search by BookID");

        add = new Button("Add \n Book");
        update = new Button("Update \n Book");
        delete = new Button("Delete \n Book");
        refresh = new Button("Refresh \n Fields");
        search = new Button("Search");
        clear = new Button("Clear \n Fields");
        back = new Button("Back");
        back.setId("backButton");
        users = new Button("Users");

        grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);

        table = new TableView<>();


        TableColumn<BooksData, String> id = new TableColumn<>("BookID");
        id.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        id.setPrefWidth(130);

        TableColumn<BooksData, String> bookName = new TableColumn<>("bookName");
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookName.setPrefWidth(130);

        TableColumn<BooksData, String> authorName = new TableColumn<>("authorName");
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorName.setPrefWidth(130);

        TableColumn<BooksData, String> edition = new TableColumn<>("edition");
        edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        edition.setPrefWidth(130);

        TableColumn<BooksData, Double> Storage = new TableColumn<>("Storage");
        Storage.setCellValueFactory(new PropertyValueFactory<>("Storage"));
        Storage.setPrefWidth(100);

        table.getColumns().addAll(id, bookName, authorName, edition, Storage);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(900);
    }

    void renderControls() {
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(bookIdLabel, 0, 1);
        grid.add(tBookID, 1, 1);
        grid.add(bookTitleLabel, 0, 2);
        grid.add(tTitle, 1, 2);
        grid.add(authorLabel, 0, 3);
        grid.add(tAuthor, 1, 3);
        grid.add(pubYearLabel, 0, 4);
        grid.add(tPubYear, 1, 4);

        grid.add(priceLabel, 0, 5);
        grid.add(tPrice, 1, 5);

        row1 = new HBox(10, add, update, delete);
        row1.setAlignment(Pos.CENTER);
        HBox hBoxRefreshAndDelete = new HBox(clear, refresh);
        hBoxRefreshAndDelete.setAlignment(Pos.CENTER);
        hBoxRefreshAndDelete.setSpacing(10);
        row2 = new HBox(10, hBoxRefreshAndDelete);
        row2.setAlignment(Pos.CENTER);
        row3 = new HBox(10, searchField, search);
        row3.setAlignment(Pos.CENTER);

        grid.add(row1, 0, 6);
        grid.add(row2, 1, 6);
        grid.add(row3, 0, 8);

        HBox hBoxUsersAndBack = new HBox(back, users);
        hBoxUsersAndBack.setAlignment(Pos.CENTER);
        hBoxUsersAndBack.setSpacing(10);
        grid.add(hBoxUsersAndBack, 0, 10);

        vbox = new VBox(10, table);
        vbox.setPadding(new Insets(20));

        rootHBox = new HBox(10, grid, vbox);
    }

    public Scene getScene() {
        adminScene = new Scene(rootHBox);
        adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        users.getStyleClass().add("button-users");

        return adminScene;
    }
}