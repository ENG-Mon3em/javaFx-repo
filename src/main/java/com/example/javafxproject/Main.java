package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        Store store = new Store();
        SignIn SignIn = new SignIn();
        SignUp SignUp = new SignUp();

//        stage.setScene(store.getScene());
//        stage.setTitle("Store");
//        stage.setScene(SignUp.getScene());
//        stage.setTitle("SignUp");
//        stage.setScene(SignIn.getScene());
//        stage.setTitle("SignIn");


        stage.setResizable(false);
        stage.show();
    }
}
