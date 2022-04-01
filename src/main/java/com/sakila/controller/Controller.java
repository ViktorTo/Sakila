package com.sakila.controller;

import com.sakila.dao.CustomerDAO;
import com.sakila.dao.FilmDAO;
import com.sakila.entity.Customer;
import com.sakila.entity.Film;
import com.sakila.entity.FilmActor;
import com.sakila.logic.Manager;
import com.sakila.main.Main;
import com.sakila.utility.SceneView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final Manager manager = new Manager();

    //Main FXML
    @FXML
    private TableColumn<Film, FilmActor> actorsCol;

    @FXML
    private Button createcustomerBtn;

    @FXML
    private Button createfilmBtn;

    @FXML
    private ChoiceBox<?> customerChoice;

    @FXML
    private Tab customerTab;

    @FXML
    private TableView<Customer> customerTbl;

    @FXML
    private TableColumn<Customer, Integer> customeridCol;

    @FXML
    private Button deletecustomerBtn;

    @FXML
    private Button deletefilmBtn;

    @FXML
    private ChoiceBox<?> filmChoice;

    @FXML
    private Tab filmTab;

    @FXML
    private TableView<Film> filmTbl;

    @FXML
    private TableColumn<Film, Integer> filmidCol;

    @FXML
    private TableColumn<Customer, String> firstnameCol;

    @FXML
    private TableColumn<Customer, String> lastnameCol;

    @FXML
    private TableColumn<Customer, Timestamp> customerUpdateCol;

    @FXML
    private TableColumn<Film, Integer> lengthCol;

    @FXML
    private TableColumn<Customer, String> mailCol;

    @FXML
    private TableColumn<Film, Integer> releaseCol;

    @FXML
    private Button rentmovieBtn;

    @FXML
    private TableColumn<Film, String> titleCol;

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
    public void deleteCustomerClicked(MouseEvent event) {
        Customer customer = customerTbl.getSelectionModel().getSelectedItem();
        if (customer != null) {
            manager.deleteCustomer(customer.getId());
        }
    }
    @FXML
    public void createFilmButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.CREATEFILM);
    }
    @FXML
    public void updateFilmButton(MouseEvent event) throws IOException {
        changeScene(event, SceneView.UPDATEFILM);
    }
    @FXML
    public void deleteFilmClicked(MouseEvent event) {
        Film film = filmTbl.getSelectionModel().getSelectedItem();
        if (film != null) {
            manager.deleteFilm(film.getId());
        }
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

    public void filmLoadTab() {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Show data in customer tab
        customeridCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        mailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        customerUpdateCol.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("lastUpdate"));
        customerTbl.setItems(manager.getAllCustomers());

        //Show data in film tab
        filmidCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
        releaseCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("releaseYear"));
        actorsCol.setCellValueFactory(new PropertyValueFactory<Film, FilmActor>("filmActors"));
        filmTbl.setItems(manager.getAllFilms());

    }
}