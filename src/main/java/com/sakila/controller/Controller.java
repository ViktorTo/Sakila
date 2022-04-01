package com.sakila.controller;

import com.sakila.main.Main;
import com.sakila.utility.SceneView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    //Main FXML
    @FXML
    private TableColumn<?, ?> actorsCol;

    @FXML
    private Button createcustomerBtn;

    @FXML
    private Button createfilmBtn;

    @FXML
    private ChoiceBox<?> customerChoice;

    @FXML
    private Tab customerTab;

    @FXML
    private TableView<?> customerTbl;

    @FXML
    private TableColumn<?, ?> customeridCol;

    @FXML
    private Button deletecustomerBtn;

    @FXML
    private Button deletefilmBtn;

    @FXML
    private ChoiceBox<?> filmChoice;

    @FXML
    private Tab filmTab;

    @FXML
    private TableView<?> filmTbl;

    @FXML
    private TableColumn<?, ?> filmidCol;

    @FXML
    private TableColumn<?, ?> firstnameCol;

    @FXML
    private TableColumn<?, ?> lastnameCol;

    @FXML
    private TableColumn<?, ?> lengthCol;

    @FXML
    private TableColumn<?, ?> mailCol;

    @FXML
    private TableColumn<?, ?> releaseCol;

    @FXML
    private Button rentmovieBtn;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private Button updatecustomerBtn;

    @FXML
    private Button updatefilmBtn;



    @FXML
    void createCustomerButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.CREATECUSTOMER);
    }
    @FXML
    public void updateCustomerButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.UPDATECUSTOMER);
    }
    @FXML
    public void createFilmButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.CREATEFILM);
    }
    @FXML
    public void updateFilmButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.UPDATEFILM);
    }

    public void changeScene(MouseEvent event, SceneView view) throws IOException {
        String fxml = "";
        switch (view) {

            case CREATECUSTOMER -> {
                fxml="sakilacreatecustomer.fxml";
            }
            case UPDATECUSTOMER -> {
                fxml="sakilaupdatecustomer.fxml";
            }
            case CREATEFILM -> {
                fxml="sakilacreatefilm.fxml";
            }
            case UPDATEFILM -> {
                fxml="sakilaupdatefilm.fxml";
            }

        }

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}