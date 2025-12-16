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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminBooks {

    public Label titleLabel;
    public Label bookIdLabel;
    public Label bookTitleLabel;
    public Label authorLabel;
    public Label pubYearLabel;
    public Label storageLabel;
    public TextField tBookID;
    public TextField tTitle;
    public TextField tAuthor;
    public TextField tPubYear;
    public TextField tStorage;
    public Button add;
    public Button update;
    public Button delete;
    public Button clear;
    public Button back;
    public Button users;
    public GridPane grid;
    public HBox row1;
    public HBox row2;
    public TableView<BooksData> table;
    public VBox vbox;
    public HBox rootHBox;
    public Scene adminScene;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ObservableList<BooksData> data;

    public AdminBooks() {
        initControls();
        initTable();
        initActions();
        renderControls();
        fetchBooksData();
    }

    void initControls() {
        titleLabel = new Label("Book Information");
        bookIdLabel = new Label("BookID:");
        bookTitleLabel = new Label("BookTitle:");
        authorLabel = new Label("Author:");
        pubYearLabel = new Label("PubYear:");
        storageLabel = new Label("Storage:");

        tBookID = new TextField();
        tTitle = new TextField();
        tAuthor = new TextField();
        tPubYear = new TextField();
        tStorage = new TextField();

        add = new Button("Add");
        update = new Button("Update");
        delete = new Button("Delete");
        clear = new Button("Clear");
        back = new Button("Back");
        users = new Button("Edit Users");

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        table = new TableView<>();
        data = FXCollections.observableArrayList();
    }

    void initTable() {
        TableColumn<BooksData, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(100);

        TableColumn<BooksData, String> bookName = new TableColumn<>("BookName");
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        TableColumn<BooksData, String> authorName = new TableColumn<>("Author");
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));

        TableColumn<BooksData, Integer> pubYear = new TableColumn<>("PubYear");
        pubYear.setCellValueFactory(new PropertyValueFactory<>("PubYear"));

        TableColumn<BooksData, Double> storage = new TableColumn<>("Storage");
        storage.setCellValueFactory(new PropertyValueFactory<>("Storage"));

        table.getColumns().addAll(id, bookName, authorName, pubYear, storage);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(900);
    }

    void fetchBooksData() {
        data.clear();
        conn = DBconn.DBConnection();
        String sql = "SELECT ID, NAME, AUTHOR, PUBYEAR, STORAGE FROM BOOKS";

        try {
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();

            while (res.next()) {
                data.add(new BooksData(res.getInt("ID"), res.getString("NAME"), res.getString("AUTHOR"), res.getDouble("STORAGE"), res.getInt("PUBYEAR")));
            }
            table.setItems(data);
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error fetching books data: " + ex.toString());
        }
    }

    void initActions() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tBookID.setText(String.valueOf(newSelection.getId()));
                tTitle.setText(newSelection.getBookName());
                tAuthor.setText(newSelection.getAuthorName());
                tPubYear.setText(String.valueOf(newSelection.getPubYear()));
                tStorage.setText(String.valueOf(newSelection.getStorage()));
            } else {
                tBookID.clear();
                tTitle.clear();
                tAuthor.clear();
                tPubYear.clear();
                tStorage.clear();
            }
        });

        clear.setOnAction(e -> {
            tBookID.clear();
            tTitle.clear();
            tAuthor.clear();
            tPubYear.clear();
            tStorage.clear();
            table.getSelectionModel().clearSelection();
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
        grid.add(storageLabel, 0, 5);
        grid.add(tStorage, 1, 5);

        row1 = new HBox(10, add, update, delete);
        row1.setAlignment(Pos.CENTER);
        row2 = new HBox(10, clear);
        row2.setAlignment(Pos.CENTER);

        VBox actionVBox = new VBox(10, row1, row2);
        actionVBox.setAlignment(Pos.CENTER);
        grid.add(actionVBox, 0, 6, 2, 1);

        HBox hBoxUsersAndBack = new HBox(10, back, users);
        hBoxUsersAndBack.setAlignment(Pos.CENTER);
        grid.add(hBoxUsersAndBack, 0, 7, 2, 1);

        vbox = new VBox(20, grid);
        vbox.setAlignment(Pos.TOP_LEFT);

        VBox tableVBox = new VBox(20, table);
        tableVBox.setAlignment(Pos.TOP_RIGHT);

        rootHBox = new HBox(20, vbox, tableVBox);
        rootHBox.setAlignment(Pos.CENTER);
        rootHBox.setPadding(new Insets(20));
    }

    public Scene getScene() {
        if (adminScene == null) {
            adminScene = new Scene(rootHBox, 1500, 800);
            adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return adminScene;
    }
}
