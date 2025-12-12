package com.example.javafxproject;

import javafx.geometry.Insets;
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
    Label genreLabel;
    Label pagesLabel;
    Label languageLabel;
    Label priceLabel;
    TextField tBookID;
    TextField tTitle;
    TextField tAuthor;
    TextField tPubYear;
    TextField tGenre;
    TextField tPages;
    TextField tLanguage;
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
    TableView<Books> table;
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
        genreLabel = new Label("Genre:");
        pagesLabel = new Label("Pages:");
        languageLabel = new Label("Language:");
        priceLabel = new Label("Storage:");

        tBookID = new TextField();
        tTitle = new TextField();
        tAuthor = new TextField();
        tPubYear = new TextField();
        tGenre = new TextField();
        tPages = new TextField();
        tLanguage = new TextField();
        tPrice = new TextField();
        searchField = new TextField();
        searchField.setPromptText("Search by BookID or Title");

        add = new Button("Add");
        update = new Button("Update");
        delete = new Button("Delete");
        refresh = new Button("Refresh");
        search = new Button("Search");
        clear = new Button("Clear");
        back = new Button("Back");
        users = new Button("Users");

        update.setDisable(true);
        delete.setDisable(true);

        grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);

        table = new TableView<>();


        TableColumn<Books, String> id = new TableColumn<>("BookID");
        id.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        id.setPrefWidth(130);

        TableColumn<Books, String> bookName = new TableColumn<>("bookName");
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookName.setPrefWidth(130);

        TableColumn<Books, String> authorName = new TableColumn<>("authorName");
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorName.setPrefWidth(130);

        TableColumn<Books, String> edition = new TableColumn<>("edition");
        edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        edition.setPrefWidth(130);

        TableColumn<Books, String> genre = new TableColumn<>("genre");
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genre.setPrefWidth(130);

        TableColumn<Books, String> pages = new TableColumn<>("pages");
        pages.setCellValueFactory(new PropertyValueFactory<>("pages"));
        pages.setPrefWidth(130);

        TableColumn<Books, String> language = new TableColumn<>("language");
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        language.setPrefWidth(130);

        TableColumn<Books, Double> Storage = new TableColumn<>("Storage");
        Storage.setCellValueFactory(new PropertyValueFactory<>("Storage"));
        Storage.setPrefWidth(100);

        table.getColumns().addAll(id, bookName, authorName, edition, genre, pages, language, Storage);
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
        grid.add(genreLabel, 0, 5);
        grid.add(tGenre, 1, 5);
        grid.add(pagesLabel, 0, 6);
        grid.add(tPages, 1, 6);
        grid.add(languageLabel, 0, 7);
        grid.add(tLanguage, 1, 7);
        grid.add(priceLabel, 0, 8);
        grid.add(tPrice, 1, 8);

        row1 = new HBox(10, add, update, delete);
        row2 = new HBox(10, refresh, clear);
        row3 = new HBox(10, searchField, search);
        grid.add(row1, 0, 9);
        grid.add(row2, 0, 10);
        grid.add(row3, 0, 11);
        grid.add(back, 0, 13);
        grid.add(users, 1, 13);

        vbox = new VBox(10, table);
        vbox.setPadding(new Insets(20));

        rootHBox = new HBox(10, grid, vbox);

        clear.setOnAction(e -> {
            tBookID.clear();
            tTitle.clear();
            tAuthor.clear();
            tPubYear.clear();
            tGenre.clear();
            tPages.clear();
            tLanguage.clear();
            tPrice.clear();
            add.setDisable(false);
            update.setDisable(true);
            delete.setDisable(true);
            tBookID.setDisable(false);
        });

        back.setOnAction(e -> {
        });

        users.setOnAction(e -> {
        });
    }

    public Scene getScene() {
        adminScene = new Scene(rootHBox, 1200, 550);
        adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        users.getStyleClass().add("button-users");

        return adminScene;
    }
}