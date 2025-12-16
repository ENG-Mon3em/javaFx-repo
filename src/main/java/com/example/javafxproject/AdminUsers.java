package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminUsers {

    public Label titleLabel;
    public Label userIdLabel;
    public Label userNameLabel;
    public Label userPasswordLabel;
    public TextField tUserID;
    public TextField tUserName;
    public TextField tUserPassword;
    public Button add;
    public Button update;
    public Button delete;
    public Button clear;
    public Button back;
    public Button users;
    public GridPane grid;
    public HBox row1;
    public HBox row2;
    public TableView<UsersData> table;
    public VBox vbox;
    public HBox rootHBox;
    public Scene adminScene;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    ObservableList<UsersData> data;

    public AdminUsers() {
        initControls();
        initTable();
        initActions();
        renderControls();
        fetchUsersData();
    }

    void initControls() {
        titleLabel = new Label("User Information");
        userIdLabel = new Label("ID:");
        userNameLabel = new Label("Name:");
        userPasswordLabel = new Label("Password:");

        tUserID = new TextField();
        tUserName = new TextField();
        tUserPassword = new PasswordField();

        add = new Button("Add");
        update = new Button("Update");
        delete = new Button("Delete");
        clear = new Button("Clear");
        back = new Button("Back");
        users = new Button("Edit Books");

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        table = new TableView<>();
        data = FXCollections.observableArrayList();
    }

    void initTable() {
        TableColumn<UsersData, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(100);

        TableColumn<UsersData, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(250);

        TableColumn<UsersData, String> userPassword = new TableColumn<>("Password");
        userPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        userPassword.setPrefWidth(250);

        table.getColumns().addAll(id, name, userPassword);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(900);
    }

    void fetchUsersData() {
        data.clear();
        conn = DBconn.DBConnection();
        String sql = "SELECT ID, NAME, PASSWORD FROM USERS";

        try {
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();

            while (res.next()) {
                data.add(new UsersData(res.getInt("ID"), res.getString("NAME"), res.getString("PASSWORD")));
            }
            table.setItems(data);
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error fetching users data: " + ex.toString());
        }
    }

    void initActions() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tUserID.setText(String.valueOf(newSelection.getId()));
                tUserName.setText(newSelection.getName());
                tUserPassword.setText(newSelection.getPassword());
            } else {
                tUserID.clear();
                tUserName.clear();
                tUserPassword.clear();
            }
        });

        clear.setOnAction(e -> {
            tUserID.clear();
            tUserName.clear();
            tUserPassword.clear();
            table.getSelectionModel().clearSelection();
        });
    }

    void renderControls() {
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(userIdLabel, 0, 1);
        grid.add(tUserID, 1, 1);

        grid.add(userNameLabel, 0, 2);
        grid.add(tUserName, 1, 2);

        grid.add(userPasswordLabel, 0, 3);
        grid.add(tUserPassword, 1, 3);

        row1 = new HBox(10, add, update, delete);
        row1.setAlignment(Pos.CENTER);
        row2 = new HBox(10, clear);
        row2.setAlignment(Pos.CENTER);

        VBox actionVBox = new VBox(10, row1, row2);
        actionVBox.setAlignment(Pos.CENTER);
        grid.add(actionVBox, 0, 4, 2, 1);

        HBox hBoxUsersAndBack = new HBox(10, back, users);
        hBoxUsersAndBack.setAlignment(Pos.CENTER);
        grid.add(hBoxUsersAndBack, 0, 5, 2, 1);

        vbox = new VBox(20, grid);
        vbox.setAlignment(Pos.TOP_LEFT);

        VBox tableVBox = new VBox(20, table);
        tableVBox.setAlignment(Pos.TOP_RIGHT);

        rootHBox = new HBox(20, vbox, tableVBox);
        rootHBox.setAlignment(Pos.CENTER);
        rootHBox.setPadding(new Insets(20));
    }

    public Scene getScene() {
        if (adminScene == null) {
            adminScene = new Scene(rootHBox, 1500, 800);
            adminScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        }
        return adminScene;
    }
}
