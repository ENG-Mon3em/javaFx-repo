package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class viewBooks {

    public Label titleLabel;
    public TextArea DetailsTextArea;
    public TableView<BooksData> table;
    public TableColumn<BooksData, Integer> id;
    public TableColumn<BooksData, String> bookName;
    public TableColumn<BooksData, String> authorName;
    public TableColumn<BooksData, Integer> pubYear;
    public TableColumn<BooksData, Double> Storage;
    public Button storeButton;
    public Button backButton;
    public VBox vBoxdetails;
    public VBox vBoxButtons;
    public VBox vBox;
    public VBox firstPart;
    public HBox secondPart;
    public FlowPane flowPane;
    public Scene viewBooksScene;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ObservableList<BooksData> data;

    viewBooks() {
        initControls();
        initTable();
        initActions();
        renderControls();
        showBooks();
    }

    void initControls() {
        titleLabel = new Label("View Books Information");

        DetailsTextArea = new TextArea();
        DetailsTextArea.setPromptText("Book Details will appear here upon selection...");
        DetailsTextArea.setEditable(false);
        DetailsTextArea.setPrefRowCount(10);
        DetailsTextArea.setPrefColumnCount(50);

        storeButton = new Button("Store");
        storeButton.setId("DownloadButton");

        backButton = new Button("Back");
        backButton.setId("backButton");

        table = new TableView<>();
    }

    void initTable() {
        id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(50);

        bookName = new TableColumn<>("Book Name");
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookName.setPrefWidth(250);

        authorName = new TableColumn<>("Author");
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorName.setPrefWidth(250);

        pubYear = new TableColumn<>("Pub Year");
        pubYear.setCellValueFactory(new PropertyValueFactory<>("pubYear"));
        pubYear.setPrefWidth(100);

        Storage = new TableColumn<>("Storage");
        Storage.setCellValueFactory(new PropertyValueFactory<>("Storage"));
        Storage.setPrefWidth(100);

        table.getColumns().addAll(bookName, authorName);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(800);
    }

    public void showBooks() {
        data = FXCollections.observableArrayList();
        conn = DBconn.DBConnection();

        String sql = "SELECT ID, NAME, AUTHOR, PUBYEAR, STORAGE FROM BOOKS";

        try {
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();

            while (res.next()) {
                data.add(new BooksData(
                        res.getInt("ID"),
                        res.getString("NAME"),
                        res.getString("AUTHOR"),
                        res.getDouble("STORAGE"),
                        res.getInt("PUBYEAR")
                ));
            }

            table.setItems(data);
            pst.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error fetching books in viewBooks: " + ex.toString());
        }
    }

    void initActions() {
        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        DetailsTextArea.setText(newSelection.toString());
                    } else {
                        DetailsTextArea.setText("Book Details will appear here upon selection...");
                    }
                }
        );
    }

    void renderControls() {
        firstPart = new VBox(titleLabel, table);
        firstPart.setAlignment(Pos.CENTER);
        firstPart.setSpacing(20);

        vBoxButtons = new VBox(storeButton, backButton);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(20);

        secondPart = new HBox(DetailsTextArea, vBoxButtons);
        secondPart.setSpacing(20);
        secondPart.setAlignment(Pos.CENTER);

        vBox = new VBox(firstPart, secondPart);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        flowPane = new FlowPane(vBox);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(20));
    }

    public Scene getScene() {
        if (viewBooksScene == null) {
            viewBooksScene = new Scene(flowPane, 1500, 800);
            viewBooksScene.getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/style.css")
                    ).toExternalForm()
            );
        }
        return viewBooksScene;
    }
}
