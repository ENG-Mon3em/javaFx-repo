package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Stage mainStage;

    @Override
    public void start(Stage stage) {

        mainStage = stage;

        AdminUsers adminUsers = new AdminUsers();
        AdminBooks adminBooks = new AdminBooks();
        Home home = new Home();
        ViewBooks viewBooks = new ViewBooks();
        Store store = new Store();
        SignIn signIn = new SignIn();
        SignUp signUp = new SignUp();



        mainStage.setScene(signIn.getScene());
        mainStage.setTitle("Library System");
        mainStage.show();
    }
}