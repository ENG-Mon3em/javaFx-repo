package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.PreparedStatement;

public class Main extends Application {
    DBconnection conn = null;
    PreparedStatement pst = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        AdminUsers adminUsers = new AdminUsers();
        AdminBooks adminBooks = new AdminBooks();
        Home home = new Home();
        viewBooks viewBooks = new viewBooks();
        Store store = new Store();
        SignIn signIn = new SignIn();
        SignUp signUp = new SignUp();

        signIn.signInButton.setOnAction(e -> stage.setScene(home.getHomeScene()));
        signIn.signUpButton.setOnAction(e -> stage.setScene(signUp.getScene()));

        signUp.signUpButton.setOnAction(e -> stage.setScene(signIn.getScene()));

        home.editUserButton.setOnAction(e -> stage.setScene(adminUsers.getScene()));
        home.editBookButton.setOnAction(e -> stage.setScene(adminBooks.getScene()));
        home.shopButton.setOnAction(e -> stage.setScene(store.getScene()));
        home.infoButton.setOnAction(e -> stage.setScene(viewBooks.getScene()));
        home.logOutButton.setOnAction(e -> stage.setScene(signIn.getScene()));

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
}