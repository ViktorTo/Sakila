package com.sakila.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

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

    // Create customer FXML
    @FXML
    private ChoiceBox<?> addressChoice;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastnameTxt;

    @FXML
    private ChoiceBox<?> storeChoice;


    @FXML
    void changeSceneButtonAction(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        if (event.getSource() == createcustomerBtn){
            root = FXMLLoader.load(getClass().getResource("sakilacreatecustomer.fxml"));
            stage = (Stage) createcustomerBtn.getScene().getWindow();

        } else if (event.getSource() == updatecustomerBtn) {
            root = FXMLLoader.load(getClass().getResource("sakilaupdatecustomer.fxml"));
            stage = (Stage) updatecustomerBtn.getScene().getWindow();

        } else if (event.getSource() == createfilmBtn) {
            root = FXMLLoader.load(getClass().getResource("sakilacreatefilm.fxml"));
            stage = (Stage) createfilmBtn.getScene().getWindow();

        } else if (event.getSource() == updatefilmBtn) {
            root = FXMLLoader.load(getClass().getResource("sakilaupdatefilm.fxml"));
            stage = (Stage) updatefilmBtn.getScene().getWindow();
        } else {
            root = FXMLLoader.load(getClass().getResource("sakilamain.fxml"));
            stage = (Stage) cancelBtn.getScene().getWindow();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}