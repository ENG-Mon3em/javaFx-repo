package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    Label StorageLabel;
    TextField tBookID;
    TextField tTitle;
    TextField tAuthor;
    TextField tPubYear;
    TextField tStorage;
    Button add;
    Button update;
    Button delete;
    Button clear;
    Button back;
    Button users;
    GridPane grid;
    HBox row1;
    HBox row2;
        TableView<BooksData> table;
    VBox vbox;
    HBox rootHBox;
    Scene adminScene;


    public AdminBooks() {
        initControls();
        initActions();
        renderControls();
    }

    void initControls() {
        titleLabel = new Label("Book Information");
        bookIdLabel = new Label("BookID:");
        bookTitleLabel = new Label("BookTitle:");
        authorLabel = new Label("Author:");
        pubYearLabel = new Label("PubYear:");
        StorageLabel = new Label("Storage(MB):");

        tBookID = new TextField();
        tTitle = new TextField();
        tAuthor = new TextField();
        tPubYear = new TextField();
        tStorage = new TextField();

        add = new Button("Add \n Book");
        update = new Button("Update \n Book");
        delete = new Button("Delete \n Book");
        clear = new Button("Clear \n Fields");
        back = new Button("Back");
        back.setId("backButton");
        users = new Button("Users");

        grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);

        table = new TableView<>();


        TableColumn<BooksData, Integer> id = new TableColumn<>("BookID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(130);

        TableColumn<BooksData, String> bookName = new TableColumn<>("BookTitle");
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookName.setPrefWidth(130);

        TableColumn<BooksData, String> authorName = new TableColumn<>("Author");
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorName.setPrefWidth(130);

        TableColumn<BooksData, String> PubYear = new TableColumn<>("PubYear");
        PubYear.setCellValueFactory(new PropertyValueFactory<>("PubYear"));
        PubYear.setPrefWidth(130);

        TableColumn<BooksData, Double> Storage = new TableColumn<>("Storage");
        Storage.setCellValueFactory(new PropertyValueFactory<>("Storage"));
        Storage.setPrefWidth(100);

        table.getColumns().addAll(id, bookName, authorName, PubYear, Storage);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(900);
    }
    void initActions(){
        add.setOnAction(e -> {
            int id = Integer.parseInt(tBookID.getText());
            String title = tTitle.getText();
            String author = tAuthor.getText();
            int year = Integer.parseInt(tPubYear.getText());
            double storage = Double.parseDouble(tStorage.getText());

            table.getItems().add(new BooksData(id, title, author, storage, year));
        });
        clear.setOnAction(e -> {
            tBookID.clear();
            tTitle.clear();
            tAuthor.clear();
            tPubYear.clear();
            tStorage.clear();
        });
        delete.setOnAction(e -> {
            BooksData selectedBook = table.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                table.getItems().remove(selectedBook);
            }
        });
        update.setOnAction((event)->{
            BooksData selected = (BooksData) table.getSelectionModel().getSelectedItem();
            if(selected != null){
                selected.setId(Integer.parseInt(tBookID.getText()));
                selected.setBookName(tTitle.getText());
                selected.setAuthorName(tAuthor.getText());
                selected.setPubYear(Integer.parseInt(tPubYear.getText()));
                selected.setStorage(Double.parseDouble(tStorage.getText()));
                table.refresh();
            }
        });
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

        grid.add(StorageLabel, 0, 5);
        grid.add(tStorage, 1, 5);

        row1 = new HBox(10, add, update, delete);
        row1.setAlignment(Pos.CENTER);
        row2 = new HBox(10, clear);
        row2.setAlignment(Pos.CENTER);

        grid.add(row1, 0, 6);
        grid.add(row2, 1, 6);

        HBox hBoxUsersAndBack = new HBox(back, users);
        hBoxUsersAndBack.setAlignment(Pos.CENTER);
        hBoxUsersAndBack.setSpacing(10);
        grid.add(hBoxUsersAndBack, 0, 10);

        vbox = new VBox(10, table);
        vbox.setPadding(new Insets(20));

        rootHBox = new HBox(10, grid, vbox);
    }

    public Scene getScene() {
        if (adminScene == null) {
            adminScene = new Scene(rootHBox,1500,800);
            adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return adminScene;
    }
}