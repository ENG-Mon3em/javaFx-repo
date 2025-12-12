package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        Home home= new Home();
        viewBooks viewBooks = new viewBooks();
        Store store = new Store();
        SignIn SignIn = new SignIn();
        SignUp SignUp = new SignUp();

        //صفحة حسين
//        stage.setScene(home.getHomeScene());
//        stage.setTitle("Home");
        //صفحة حسن
//        stage.setScene(viewBooks.getScene());
//        stage.setTitle("viewBooks");
        //صفحة منعم
//        stage.setScene(store.getScene());
//        stage.setTitle("Store");
        //صفحة شهاب
//        stage.setScene(SignUp.getScene());
//        stage.setTitle("SignUp");
        //صفحة سيد
//        stage.setScene(SignIn.getScene());
//        stage.setTitle("SignIn");


//        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
    }
}
