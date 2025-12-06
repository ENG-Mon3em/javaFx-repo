package com.example.javafxproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class Main extends Application {

    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label email = new Label("Email");
        Label pass = new Label("Password");
        Label space = new Label();
        TextField emailT = new TextField();
        PasswordField passT = new PasswordField();
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signIn.setPrefWidth(200);
        signUp.setPrefWidth(200);
        Image im = new Image(Objects.requireNonNull(getClass().getResource("/Library.jpg")).toString());
        ImageView imv = new ImageView(im);
        imv.setFitHeight(200);
        imv.setFitWidth(300);

        GridPane g1 = new GridPane();
        VBox buttonBox = new VBox(10, signIn, signUp);
        g1.add(imv,0,0,2,1);
        g1.add(email,0,2);
        g1.add(pass,0,3);
        g1.add(emailT,1,2);
        g1.add(passT,1,3);
        g1.add(space, 0, 4);
        g1.add(buttonBox, 0, 5, 2, 1);
        g1.setHgap(10);
        g1.setVgap(10);
        g1.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(g1,400,600);
        scene.getStylesheets().add(new File("src/main/resources/style.css").toURI().toString());
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }
}
