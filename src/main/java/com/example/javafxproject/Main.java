package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        Shop shop = new Shop();
        SignIn SignIn = new SignIn();
        SignUp SignUp = new SignUp();

//        stage.setScene(shop.getScene()); سعد(فك الكومنت عشان تشوف الصفحة)
//        stage.setScene(SignUp.getScene()); شهاب(فك الكومنت عشان تشوف الصفحة)
//        stage.setScene(SignIn.getScene()); سيد(فك الكومنت عشان تشوف الصفحة)


        stage.show();
    }
}
