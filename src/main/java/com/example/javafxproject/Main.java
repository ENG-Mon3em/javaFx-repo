package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {

        SignIn signIn = new SignIn();
        stage.setScene(signIn.getScene());
        stage.setTitle("Sign In");
        stage.show();
    }
}
