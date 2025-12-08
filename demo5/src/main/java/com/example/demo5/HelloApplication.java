// page admin 1
package com.example.demo5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

import static com.example.demo5.DB205.dbconn;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Labels
        Label titleLabel = new Label("Book Information");
        Label bookIdLabel = new Label("Book ID:");
        Label bookTitleLabel = new Label("Title:");
        Label authorLabel = new Label("Author:");
        Label pubYearLabel = new Label("Publication Year:");
        Label genreLabel = new Label("Genre:");
        Label pagesLabel = new Label("Pages:");
        Label languageLabel = new Label("Language:");
        Label priceLabel = new Label("Price:");
        // -------------------- Fields --------------------
        TextField tBookID = new TextField();
        TextField tTitle = new TextField();
        TextField tAuthor = new TextField();
        TextField tPubYear = new TextField();
        TextField tGenre = new TextField();
        TextField tPages = new TextField();
        TextField tLanguage = new TextField();
        TextField tPrice = new TextField();
        TextField searchField = new TextField();
        searchField.setPromptText("Search by BookID or Title");

        Button add = new Button("Add");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button refresh = new Button("Refresh");
        Button search = new Button("Search");
        Button clear = new Button("Clear");
        Button back = new Button("Back");
        Button users = new Button("Users");

        update.setDisable(true);
        delete.setDisable(true);

        // -------------------- Layout --------------------
        // GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);

        // Add Labels & TextFields
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(bookIdLabel, 0, 1); grid.add(tBookID, 1, 1);
        grid.add(bookTitleLabel, 0, 2); grid.add(tTitle, 1, 2);
        grid.add(authorLabel, 0, 3); grid.add(tAuthor, 1, 3);
        grid.add(pubYearLabel, 0, 4); grid.add(tPubYear, 1, 4);
        grid.add(genreLabel, 0, 5); grid.add(tGenre, 1, 5);
        grid.add(pagesLabel, 0, 6); grid.add(tPages, 1, 6);
        grid.add(languageLabel, 0, 7); grid.add(tLanguage, 1, 7);
        grid.add(priceLabel, 0, 8); grid.add(tPrice, 1, 8);

        // Buttons
        HBox row1 = new HBox(10, add, update, delete);
        HBox row2 = new HBox(10, refresh, clear);
        HBox row3 = new HBox(10, searchField, search);

        grid.add(row1, 0, 9, 2, 1);
        grid.add(row2, 0, 10, 2, 1);
        grid.add(row3, 0, 11, 2, 1);
        grid.add(back, 0, 13, 2, 1);
        grid.add(users, 1, 13, 2, 1);
        // -------------------- Table --------------------
        TableView<Book> table = new TableView<>();
        table.getColumns().addAll(
                createColumn("Book ID", "bookid"),
                createColumn("Title", "title"),
                createColumn("Author", "author"),
                createColumn("Publication Year", "publicationyear"),
                createColumn("Genre", "genre"),
                createColumn("Pages", "pages"),
                createColumn("Language", "language"),
                createColumnPrice()
        );
        table.setPrefWidth(900);

        // -------------------- Row Selection --------------------
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, b) -> {
            if (b != null) {
                tBookID.setText(b.getBookid());
                tTitle.setText(b.getTitle());
                tAuthor.setText(b.getAuthor());
                tPubYear.setText(b.getPublicationyear());
                tGenre.setText(b.getGenre());
                tPages.setText(String.valueOf(b.getPages()));
                tLanguage.setText(b.getLanguage());
                tPrice.setText(String.valueOf(b.getPrice()));

                add.setDisable(true);
                update.setDisable(false);
                delete.setDisable(false);
                tBookID.setDisable(true);
            }
        });

        // -------------------- Button Actions --------------------
        add.setOnAction(e -> {
            if (!validateInputs(tBookID, tTitle, tAuthor, tPubYear, tGenre, tPages, tLanguage, tPrice)) {
                showAlert("Invalid input! Check your data.");
                return;
            }
            executeUpdate("INSERT INTO Book VALUES (?,?,?,?,?,?,?,?)", tBookID, tTitle, tAuthor, tPubYear, tGenre, tPages, tLanguage, tPrice);
            refreshTable(table);
        });

        update.setOnAction(e -> {
            Book sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) return;
            if (!validateInputs(tBookID, tTitle, tAuthor, tPubYear, tGenre, tPages, tLanguage, tPrice)) {
                showAlert("Invalid input! Check your data.");
                return;
            }
            String sql = """
        UPDATE Book SET title=?, author=?, publicationyear=?,
        genre=?, pages=?, language=?, price=? WHERE bookid=?
    """;
            try (Connection conn = dbconn(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, tTitle.getText());
                pst.setString(2, tAuthor.getText());
                pst.setString(3, tPubYear.getText());
                pst.setString(4, tGenre.getText());
                pst.setInt(5, Integer.parseInt(tPages.getText()));
                pst.setString(6, tLanguage.getText());
                pst.setDouble(7, Double.parseDouble(tPrice.getText()));
                pst.setString(8, sel.getBookid());
                pst.executeUpdate();
                refreshTable(table);
                clearForm(tBookID, tTitle, tAuthor, tPubYear, tGenre, tPages, tLanguage, tPrice, add, update, delete);
            } catch (Exception ex) { ex.printStackTrace(); }
        });


        delete.setOnAction(e -> {
            Book sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) return;
            String sql = "DELETE FROM Book WHERE bookid=?";
            try (Connection conn = dbconn(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, sel.getBookid());
                pst.executeUpdate();
                refreshTable(table);
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        refresh.setOnAction(e ->
                refreshTable(table));

        search.setOnAction(e ->
                searchBooks(searchField.getText(), table));

        clear.setOnAction(e ->
                clearForm(tBookID, tTitle, tAuthor, tPubYear, tGenre, tPages, tLanguage, tPrice, add, update, delete));
        // --------------------  //back  -------------------
        back.setOnAction(e ->
                primaryStage.close());
        // -------------------   //buy   ---------------------
        users.setOnAction(e -> {
            // جاهز للربط بالصفحة الجديدة
        });

        // -------------------- Scene Setup --------------------
        VBox vbox = new VBox(10, table);
        vbox.setPadding(new Insets(20));

        HBox root = new HBox(10, grid, vbox);
        Scene scene = new Scene(root, 1200, 550);
        //Css
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        users.getStyleClass().add("button-users");
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(scene);
        primaryStage.show();

        refreshTable(table);
    }

    // -------------------- Helper Methods --------------------
    private TableColumn<Book, String> createColumn(String title, String field) {
        TableColumn<Book, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(field));
        col.setPrefWidth(130);
        return col;
    }

    private TableColumn<Book, Double> createColumnPrice() {
        TableColumn<Book, Double> col = new TableColumn<>("Price");
        col.setCellValueFactory(new PropertyValueFactory<>("price"));
        col.setPrefWidth(100);
        return col;
    }

    private void refreshTable(TableView<Book> table) {
        table.getItems().clear();
        String sql = "SELECT * FROM Book";
        try (Connection conn = dbconn();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                table.getItems().add(new Book(
                        rs.getString("bookid"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publicationyear"),
                        rs.getString("genre"),
                        rs.getString("pages"),
                        rs.getString("language"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private boolean validateInputs(TextField bookID, TextField title, TextField author,
                                   TextField pubYear, TextField genre, TextField pages,
                                   TextField language, TextField price) {
        try {
            if (bookID.getText().isEmpty() || title.getText().isEmpty() || author.getText().isEmpty()
                    || pubYear.getText().isEmpty() || genre.getText().isEmpty()
                    || language.getText().isEmpty()) return false;
            Integer.parseInt(pages.getText());
            Double.parseDouble(price.getText());
        } catch (Exception e) { return false; }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm(TextField bookID, TextField title, TextField author, TextField pubYear,
                           TextField genre, TextField pages, TextField language, TextField price,
                           Button add, Button update, Button delete) {
        bookID.clear();
        title.clear();
        author.clear();
        pubYear.clear();
        genre.clear();
        pages.clear();
        language.clear();
        price.clear();

        add.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);
        bookID.setDisable(false);
    }

    private void searchBooks(String keyword, TableView<Book> table) {
        if (keyword.isEmpty()) {
            refreshTable(table);
            return;
        }

        String sql = "SELECT * FROM Book WHERE bookid LIKE ? OR title LIKE ?";

        try (Connection conn = dbconn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            table.getItems().clear();
            while (rs.next()) {
                table.getItems().add(new Book(
                        rs.getString("bookid"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publicationyear"),
                        rs.getString("genre"),
                        rs.getString("pages"),
                        rs.getString("language"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void executeUpdate(String sql, TextField... fields) {
        try (Connection conn = dbconn(); PreparedStatement pst = conn.prepareStatement(sql)) {
            for (int i = 0; i < fields.length; i++) {
                String text = fields[i].getText();
                if (i == 5) pst.setInt(i + 1, Integer.parseInt(text)); // pages
                else if (i == 7) pst.setDouble(i + 1, Double.parseDouble(text)); // price
                else pst.setString(i + 1, text);
            }
            pst.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
