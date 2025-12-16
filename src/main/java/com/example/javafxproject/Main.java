package com.example.javafxproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet res = null;

    ObservableList<UsersData> usersData;
    ObservableList<BooksData> booksData;

    AdminUsers adminUsers;
    AdminBooks adminBooks;
    Home home;
    viewBooks viewBooks;
    Store store;
    SignIn signIn;
    SignUp signUp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        adminUsers = new AdminUsers();
        adminBooks = new AdminBooks();
        home = new Home();
        viewBooks = new viewBooks();
        store = new Store();
        signIn = new SignIn();
        signUp = new SignUp();

        try {
            loadAllData();
        } catch (SQLException ex) {
            System.out.println("Error loading initial data: " + ex.toString());
            showAlert(AlertType.ERROR, "Database Error", "Failed to connect to the database or load initial data. Check DBconn.java and table structure.");
        }

        home.editUserButton.setOnAction(e -> stage.setScene(adminUsers.getScene()));
        home.editBookButton.setOnAction(e -> stage.setScene(adminBooks.getScene()));
        home.shopButton.setOnAction(e -> stage.setScene(store.getScene()));
        home.infoButton.setOnAction(e -> stage.setScene(viewBooks.getScene()));
        home.logOutButton.setOnAction(e -> stage.setScene(signIn.getScene()));

        signIn.signInButton.setOnAction(e -> {
            String name = signIn.emailTextField.getText();
            String password = signIn.passwordField.getText();

            String sql = "SELECT * FROM USERS WHERE NAME = ? AND PASSWORD = ?";

            conn = DBconn.DBConnection();
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, password);
                res = pst.executeQuery();

                if (res.next()) {
                    stage.setScene(home.getHomeScene());
                } else {
                    showAlert(AlertType.ERROR, "Login Error", "Wrong account, please SignUp.");
                    signIn.emailTextField.setText("");
                    signIn.passwordField.setText("");
                }

                res.close();
                pst.close();
                conn.close();
            } catch (Exception ex) {
                System.out.println("Sign In Error: " + ex.toString());
                showAlert(AlertType.ERROR, "System Error", "An error occurred during sign in. Check DBconn and table structure.");
            }
        });

        signIn.signUpButton.setOnAction(e -> stage.setScene(signUp.getScene()));

        signUp.signUpButton.setOnAction(e -> {
            if (!signUp.passwordTextField.getText().equals(signUp.confirmPasswordField.getText())) {
                showAlert(AlertType.ERROR, "Error", "Passwords do not match!");
                return;
            }

            String id = signUp.idTextfield.getText();
            String name = signUp.nameTextField.getText();
            String password = signUp.passwordTextField.getText();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty()) {
                showAlert(AlertType.WARNING, "Input Error", "Please fill all fields.");
                return;
            }

            conn = DBconn.DBConnection();
            String sql = "INSERT INTO USERS (ID, NAME, PASSWORD) VALUES (?, ?, ?)";

            try {
                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(id));
                pst.setString(2, name);
                pst.setString(3, password);

                int i = pst.executeUpdate();
                if (i == 1) {
                    showAlert(AlertType.INFORMATION, "Success", "Sign Up Successful! Please Sign In.");
                    stage.setScene(signIn.getScene());
                    loadAllData();
                } else {
                    showAlert(AlertType.ERROR, "Error", "Sign Up Failed.");
                }

                pst.close();
                conn.close();
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Input Error", "ID must be a valid number.");
            } catch (Exception ex) {
                System.out.println("Sign Up Error: " + ex.toString());
                showAlert(AlertType.ERROR, "Database Error", "User ID already exists or database error.");
            }
        });

        adminBooks.table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                BooksData selectedBook = adminBooks.table.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    adminBooks.tBookID.setText(String.valueOf(selectedBook.getId()));
                    adminBooks.tTitle.setText(selectedBook.getBookName());
                    adminBooks.tAuthor.setText(selectedBook.getAuthorName());
                    adminBooks.tPubYear.setText(String.valueOf(selectedBook.getPubYear()));
                    adminBooks.tStorage.setText(String.valueOf(selectedBook.getStorage()));
                }
            }
        });

        adminBooks.clear.setOnAction(e -> {
            adminBooks.tBookID.setText("");
            adminBooks.tTitle.setText("");
            adminBooks.tAuthor.setText("");
            adminBooks.tPubYear.setText("");
            adminBooks.tStorage.setText("");
        });

        adminBooks.add.setOnAction(e -> {
            if (adminBooks.tBookID.getText().isEmpty() || adminBooks.tTitle.getText().isEmpty() || adminBooks.tAuthor.getText().isEmpty() || adminBooks.tPubYear.getText().isEmpty() || adminBooks.tStorage.getText().isEmpty()) {
                showAlert(AlertType.WARNING, "Input Error", "Please fill all book fields.");
                return;
            }

            conn = DBconn.DBConnection();
            String sql = "INSERT INTO BOOKS (ID, NAME, AUTHOR, PUBYEAR, STORAGE) VALUES (?, ?, ?, ?, ?)";

            try {
                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(adminBooks.tBookID.getText()));
                pst.setString(2, adminBooks.tTitle.getText());
                pst.setString(3, adminBooks.tAuthor.getText());
                pst.setInt(4, Integer.parseInt(adminBooks.tPubYear.getText()));
                pst.setDouble(5, Double.parseDouble(adminBooks.tStorage.getText()));

                if (pst.executeUpdate() == 1) {
                    showAlert(AlertType.INFORMATION, "Success", "Book Added Successfully!");
                    loadAllData();
                    adminBooks.clear.fire();
                }

                pst.close();
                conn.close();
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Input Error", "ID, PubYear, and Storage must be valid numbers.");
            } catch (Exception ex) {
                System.out.println("Error adding book: " + ex.toString());
                showAlert(AlertType.ERROR, "Database Error", "Book ID already exists or database error.");
            }
        });

        adminBooks.update.setOnAction(e -> {
            BooksData bookSelected = adminBooks.table.getSelectionModel().getSelectedItem();

            if (bookSelected != null) {
                if (adminBooks.tBookID.getText().isEmpty() || adminBooks.tTitle.getText().isEmpty() || adminBooks.tAuthor.getText().isEmpty() || adminBooks.tPubYear.getText().isEmpty() || adminBooks.tStorage.getText().isEmpty()) {
                    showAlert(AlertType.WARNING, "Input Error", "Please fill all book fields for update.");
                    return;
                }

                int oldId = bookSelected.getId();

                conn = DBconn.DBConnection();
                String updateQuery = "UPDATE BOOKS SET ID=?, NAME=?, AUTHOR=?, PUBYEAR=?, STORAGE=? WHERE ID=?";

                try {
                    pst = conn.prepareStatement(updateQuery);
                    pst.setInt(1, Integer.parseInt(adminBooks.tBookID.getText()));
                    pst.setString(2, adminBooks.tTitle.getText());
                    pst.setString(3, adminBooks.tAuthor.getText());
                    pst.setInt(4, Integer.parseInt(adminBooks.tPubYear.getText()));
                    pst.setDouble(5, Double.parseDouble(adminBooks.tStorage.getText()));
                    pst.setInt(6, oldId);

                    if (pst.executeUpdate() == 1) {
                        showAlert(AlertType.INFORMATION, "Success", "Book Updated Successfully!");
                        loadAllData();
                    } else {
                        showAlert(AlertType.ERROR, "Update Error", "No record updated.");
                    }

                    pst.close();
                    conn.close();
                } catch (NumberFormatException ex) {
                    showAlert(AlertType.ERROR, "Input Error", "ID, PubYear, and Storage must be valid numbers.");
                } catch (SQLException ex) {
                    System.out.println("Error updating book: " + ex.toString());
                    showAlert(AlertType.ERROR, "Database Error", "Error updating book in database.");
                }
            } else {
                showAlert(AlertType.WARNING, "Selection Error", "Please select a book to update.");
            }
        });

        adminBooks.delete.setOnAction(e -> {
            BooksData bookSelected = adminBooks.table.getSelectionModel().getSelectedItem();

            if (bookSelected != null) {
                conn = DBconn.DBConnection();
                String deleteQuery = "DELETE FROM BOOKS WHERE ID=?";

                try {
                    pst = conn.prepareStatement(deleteQuery);
                    pst.setInt(1, bookSelected.getId());

                    if (pst.executeUpdate() == 1) {
                        showAlert(AlertType.INFORMATION, "Success", "Book Deleted Successfully!");
                        loadAllData();
                        adminBooks.clear.fire();
                    }

                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error deleting book: " + ex.toString());
                    showAlert(AlertType.ERROR, "Database Error", "Error deleting book from database.");
                }
            } else {
                showAlert(AlertType.WARNING, "Selection Error", "Please select a book to delete.");
            }
        });

        adminUsers.table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                UsersData selectedUser = adminUsers.table.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    adminUsers.tUserID.setText(String.valueOf(selectedUser.getId()));
                    adminUsers.tUserName.setText(selectedUser.getName());
                    adminUsers.tUserPassword.setText(selectedUser.getPassword());
                }
            }
        });

        adminUsers.clear.setOnAction(e -> {
            adminUsers.tUserID.setText("");
            adminUsers.tUserName.setText("");
            adminUsers.tUserPassword.setText("");
        });

        adminUsers.add.setOnAction(e -> {
            if (adminUsers.tUserID.getText().isEmpty() || adminUsers.tUserName.getText().isEmpty() || adminUsers.tUserPassword.getText().isEmpty()) {
                showAlert(AlertType.WARNING, "Input Error", "Please fill all user fields.");
                return;
            }

            conn = DBconn.DBConnection();
            String sql = "INSERT INTO USERS (ID, NAME, PASSWORD) VALUES (?, ?, ?)";

            try {
                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(adminUsers.tUserID.getText()));
                pst.setString(2, adminUsers.tUserName.getText());
                pst.setString(3, adminUsers.tUserPassword.getText());

                if (pst.executeUpdate() == 1) {
                    showAlert(AlertType.INFORMATION, "Success", "User Added Successfully!");
                    loadAllData();
                    adminUsers.clear.fire();
                }

                pst.close();
                conn.close();
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Input Error", "ID must be a valid number.");
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.toString());
                showAlert(AlertType.ERROR, "Database Error", "User ID already exists or database error.");
            }
        });

        adminUsers.update.setOnAction(e -> {
            UsersData userSelected = adminUsers.table.getSelectionModel().getSelectedItem();

            if (userSelected != null) {
                if (adminUsers.tUserID.getText().isEmpty() || adminUsers.tUserName.getText().isEmpty() || adminUsers.tUserPassword.getText().isEmpty()) {
                    showAlert(AlertType.WARNING, "Input Error", "Please fill all user fields for update.");
                    return;
                }

                int oldId = userSelected.getId();

                conn = DBconn.DBConnection();
                String updateQuery = "UPDATE USERS SET ID=?, NAME=?, PASSWORD=? WHERE ID=?";

                try {
                    pst = conn.prepareStatement(updateQuery);
                    pst.setInt(1, Integer.parseInt(adminUsers.tUserID.getText()));
                    pst.setString(2, adminUsers.tUserName.getText());
                    pst.setString(3, adminUsers.tUserPassword.getText());
                    pst.setInt(4, oldId);

                    if (pst.executeUpdate() == 1) {
                        showAlert(AlertType.INFORMATION, "Success", "User Updated Successfully!");
                        loadAllData();
                    } else {
                        showAlert(AlertType.ERROR, "Update Error", "No record updated.");
                    }

                    pst.close();
                    conn.close();
                } catch (NumberFormatException ex) {
                    showAlert(AlertType.ERROR, "Input Error", "ID must be a valid number.");
                } catch (SQLException ex) {
                    System.out.println("Error updating user: " + ex.toString());
                    showAlert(AlertType.ERROR, "Database Error", "Error updating user in database.");
                }
            } else {
                showAlert(AlertType.WARNING, "Selection Error", "Please select a user to update.");
            }
        });

        adminUsers.delete.setOnAction(e -> {
            UsersData userSelected = adminUsers.table.getSelectionModel().getSelectedItem();

            if (userSelected != null) {
                conn = DBconn.DBConnection();
                String deleteQuery = "DELETE FROM USERS WHERE ID=?";

                try {
                    pst = conn.prepareStatement(deleteQuery);
                    pst.setInt(1, userSelected.getId());

                    if (pst.executeUpdate() == 1) {
                        showAlert(AlertType.INFORMATION, "Success", "User Deleted Successfully!");
                        loadAllData();
                        adminUsers.clear.fire();
                    }

                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error deleting user: " + ex.toString());
                    showAlert(AlertType.ERROR, "Database Error", "Error deleting user from database.");
                }
            } else {
                showAlert(AlertType.WARNING, "Selection Error", "Please select a user to delete.");
            }
        });

        adminUsers.back.setOnAction(e -> stage.setScene(home.getHomeScene()));
        adminUsers.users.setOnAction(e -> stage.setScene(adminBooks.getScene()));

        adminBooks.back.setOnAction(e -> stage.setScene(home.getHomeScene()));
        adminBooks.users.setOnAction(e -> stage.setScene(adminUsers.getScene()));

        store.backButton.setOnAction(e -> stage.setScene(home.getHomeScene()));

        viewBooks.backButton.setOnAction(e -> stage.setScene(home.getHomeScene()));
        viewBooks.storeButton.setOnAction(e -> stage.setScene(store.getScene()));

        stage.setScene(signIn.getScene());
        stage.setTitle("Library System");
        stage.show();
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadAllData() throws SQLException {
        showBooks();

        if (adminBooks.table != null) {
            adminBooks.table.setItems(booksData);
        }
        if (store.table != null) {
            store.table.setItems(booksData);
        }
        if (viewBooks.table != null) {
            viewBooks.table.setItems(booksData);
        }

        showUsers();

        if (adminUsers.table != null) {
            adminUsers.table.setItems(usersData);
        }
    }

    public void showBooks() throws SQLException {
        booksData = FXCollections.observableArrayList();
        conn = DBconn.DBConnection();
        if (conn == null) throw new SQLException("Database connection failed.");

        pst = conn.prepareStatement("SELECT ID, NAME, AUTHOR, PUBYEAR, STORAGE FROM BOOKS");
        res = pst.executeQuery();

        while (res.next()) {
            booksData.add(new BooksData(res.getInt("ID"), res.getString("NAME"), res.getString("AUTHOR"), res.getDouble("STORAGE"), res.getInt("PUBYEAR")));
        }

        res.close();
        pst.close();
        conn.close();
    }

    public void showUsers() throws SQLException {
        usersData = FXCollections.observableArrayList();
        conn = DBconn.DBConnection();
        if (conn == null) throw new SQLException("Database connection failed.");

        pst = conn.prepareStatement("SELECT ID, NAME, PASSWORD FROM USERS");
        res = pst.executeQuery();

        while (res.next()) {
            usersData.add(new UsersData(res.getInt("ID"), res.getString("NAME"), res.getString("PASSWORD")));
        }

        res.close();
        pst.close();
        conn.close();
    }
}
