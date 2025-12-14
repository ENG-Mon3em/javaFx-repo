package com.example.javafxproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class AdminUsers {

    Label titleLabel;
    Label userIdLabel;
    Label userNameLabel;
    Label userTypeLabel;

    TextField tUserID;
    TextField tUserName;
    TextField tUserType;

    TextField searchField;
    Button add;
    Button update;
    Button delete;
    Button refresh;
    Button search;
    Button clear;
    Button back;
    Button users;
    GridPane grid;
    HBox row1;
    HBox row2;
    HBox row3;

    TableView<UsersData> table;

    VBox vbox;
    HBox rootHBox;
    Scene adminScene;


    public AdminUsers() {
        initControls();
        initActions();
        renderControls();
    }

    void initControls() {
        titleLabel = new Label("User Information");
        userIdLabel = new Label("ID:");
        userNameLabel = new Label("Name:");
        userTypeLabel = new Label("User Type:");

        tUserID = new TextField();
        tUserName = new TextField();
        tUserType = new TextField();

        searchField = new TextField();
        searchField.setPromptText("Search by User ID");

        add = new Button("Add \n User");
        update = new Button("Update \n Users");
        delete = new Button("Delete \n User");
        refresh = new Button("Refresh \n Fields");
        search = new Button("Search");
        clear = new Button("Clear \n Fields");
        back = new Button("Back");
        back.setId("backButton");
        users = new Button("Books");

        grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);

        table = new TableView<>();


        TableColumn<UsersData, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(200);

        TableColumn<UsersData, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(200);

        TableColumn<UsersData, String> userType = new TableColumn<>("User Type");
        userType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        userType.setPrefWidth(250);

        table.getColumns().addAll(id, name, userType);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(900);
    }

    void initActions(){

        add.setOnAction(e -> {
            int id = Integer.parseInt(tUserID.getText());
            String name = tUserName.getText();
            String type = tUserType.getText();
            table.getItems().addAll(new UsersData(id,name,type));
        });
        clear.setOnAction(e -> {
            tUserID.clear();
            tUserName.clear();
            tUserType.clear();
        });
        update.setOnAction((event)->{
            UsersData selected = (UsersData) table.getSelectionModel().getSelectedItem();
            if(selected != null){
                selected.setName(tUserName.getText());
                selected.setId(Integer.parseInt(tUserID.getText()));
                selected.setUserType(tUserType.getText());

                table.refresh();
            }
        });


    }
    void renderControls() {
        grid.add(titleLabel, 0, 0, 2, 1);

        grid.add(userIdLabel, 0, 1);
        grid.add(tUserID, 1, 1);

        grid.add(userNameLabel, 0, 2);
        grid.add(tUserName, 1, 2);

        grid.add(userTypeLabel, 0, 3);
        grid.add(tUserType, 1, 3);

        row1 = new HBox(10, add, update, delete);
        row1.setAlignment(Pos.CENTER);
        HBox hBoxRefreshAndDelete = new HBox(clear, refresh);
        hBoxRefreshAndDelete.setAlignment(Pos.CENTER);
        hBoxRefreshAndDelete.setSpacing(10);
        row2 = new HBox(10, hBoxRefreshAndDelete);
        row2.setAlignment(Pos.CENTER);
        row3 = new HBox(10, searchField, search);
        row3.setAlignment(Pos.CENTER);

        grid.add(row1, 0, 4);
        grid.add(row2, 1, 4);
        grid.add(row3, 0, 6);

        HBox hBoxUsersAndBack = new HBox(back, users);
        hBoxUsersAndBack.setAlignment(Pos.CENTER);
        hBoxUsersAndBack.setSpacing(10);
        grid.add(hBoxUsersAndBack, 0, 8);

        vbox = new VBox(10, table);
        vbox.setPadding(new Insets(20));

        rootHBox = new HBox(10, grid, vbox);
    }

    public Scene getScene() {
        if (adminScene == null) {
            adminScene = new Scene(rootHBox,1500,800);
            adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return adminScene;
    }
}