package com.sakila.controller;

import com.sakila.dao.CustomerDAO;
import com.sakila.dao.FilmDAO;
import com.sakila.dao.StaffDAO;
import com.sakila.entity.*;
import com.sakila.logic.Manager;
import com.sakila.main.Main;
import com.sakila.utility.SceneChanger;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();

    //Main FXML
    @FXML
    private TableColumn<Film, FilmActor> actorsCol;

    @FXML
    private Tab customerTab, filmTab, staffTab, rentalTab, actorTab;

    @FXML
    private TabPane tabpane;

    @FXML
    private TableView<Customer> customerTbl;

    @FXML
    private TableColumn<Customer, Integer> customeridCol;

    @FXML
    private TableView<Film> filmTbl;

    @FXML
    private TableColumn<Customer, String> firstnameCol, lastnameCol, mailCol;

    @FXML
    private TableColumn<Customer, Timestamp> customerUpdateCol;

    @FXML
    private TableColumn<Film, Timestamp> lastupdateCol;

    @FXML
    private TableColumn<Film, Integer> lengthCol, releaseCol, filmidCol;

    @FXML
    private TableColumn<Film, String> titleCol;

    @FXML
    private TableView<Staff> staffTbl;

    @FXML
    private TableColumn<Staff, Integer> staffAddressCol;

    @FXML
    private TableColumn<Staff, String> staffEmailCol;

    @FXML
    private TableColumn<Staff, String> staffFirstNameCol;

    @FXML
    private TableColumn<Staff, Integer> staffIdCol;

    @FXML
    private TableColumn<Staff, String> staffLastNameCol;

    @FXML
    private TableColumn<Staff, String> staffPasswordCol;

    @FXML
    private TableColumn<Staff, Integer> staffStoreCol;

    @FXML
    private TableColumn<Staff, String> staffUsernameCol;

    @FXML
    private TableColumn<Rental, Customer> rentalCustomerIdCol;

    @FXML
    private TableColumn<Rental, Integer> rentalIdCol;

    @FXML
    private TableColumn<Rental, Timestamp> rentalLastUpdateCol,rentalRentalDateCol, rentalReturnCol;

    @FXML
    private TableColumn<Rental, Staff> rentalStaffIdCol;

    @FXML
    private TableView<Rental> rentalTbl;

    @FXML
    private TableColumn<Actor, String> actorFirstNameCol;

    @FXML
    private TableColumn<Actor, Integer> actorIdCol;

    @FXML
    private TableColumn<Actor, String> actorLastNameCol;

    @FXML
    private TableView<Actor> actorTbl;






    public void changeSceneCustomer(MouseEvent event, SceneView view, Customer customer) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATECUSTOMER -> {
                fxml = "sakilacreatecustomer.fxml";
            }
            case UPDATECUSTOMER -> {
                fxml = "sakilaupdatecustomer.fxml";
            }
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        CustomerController controller = loader.getController();
        controller.initData(view, customer);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneFilm(MouseEvent event, SceneView view, Film film) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATEFILM -> {
                fxml = "sakilacreatefilm.fxml";
            }
            case UPDATEFILM -> {
                fxml = "sakilaupdatefilm.fxml";
            }
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        FilmController controller = loader.getController();
        controller.initData(view, film);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneRental(MouseEvent event, SceneView view, Rental rental) throws IOException {
        String fxml = "";
        switch(view) {
            case CREATERENTAL -> {
                fxml = "sakilacreaterental.fxml";
            }
            case UPDATERENTAL -> {
                fxml = "sakilaupdaterental.fxml";
            }

        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        RentalController controller = loader.getController();
        controller.initData(view, rental);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeToFilmTab() {
        //Show data in film tab
        if (filmTab.isSelected()) {
            filmTbl.getItems().clear();
            filmidCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
            titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
            lengthCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
            releaseCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("releaseYear"));
            actorsCol.setCellValueFactory(new PropertyValueFactory<Film, FilmActor>("filmActors"));
            lastupdateCol.setCellValueFactory(new PropertyValueFactory<Film, Timestamp>("lastUpdate"));
            filmTbl.setItems(manager.getAllFilms());
        }
    }

    public void changeToCustomerTab() {
        //Show data in customer tab
        if (customerTab.isSelected()) {
            customerTbl.getItems().clear();
            customeridCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
            firstnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
            lastnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
            mailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
            customerUpdateCol.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("lastUpdate"));
            customerTbl.setItems(manager.getAllCustomers());
        }
    }

    public void changeToStaffTab() {
        //Show data in staff tab
        if (staffTab.isSelected()) {
            staffTbl.getItems().clear();
            staffIdCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
            staffFirstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
            staffLastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
 //           staffAddressCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("address"));
            staffEmailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
//            staffStoreCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("store_id"));
            staffUsernameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("username"));
            staffPasswordCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("password"));
            staffTbl.setItems(manager.getAllStaff());
        }
    }

    public void changeToRentalTab() {
        //Show data in rental tab
        if (rentalTab.isSelected()) {
            rentalTbl.getItems().clear();
            rentalIdCol.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("id"));
            rentalCustomerIdCol.setCellValueFactory(new PropertyValueFactory<Rental, Customer>("customer"));
            rentalStaffIdCol.setCellValueFactory(new PropertyValueFactory<Rental, Staff>("staff"));
            rentalRentalDateCol.setCellValueFactory(new PropertyValueFactory<Rental, Timestamp>("rentalDate"));
            rentalReturnCol.setCellValueFactory(new PropertyValueFactory<Rental, Timestamp>("returnDate"));
            rentalLastUpdateCol.setCellValueFactory(new PropertyValueFactory<Rental, Timestamp>("lastUpdate"));
            rentalTbl.setItems(manager.getRentalCustomerAndStaff());

        }
    }

    public void changeToActorTab(){
        //Show data in actor tab
        if (actorTab.isSelected()) {
            actorTbl.getItems().clear();
            actorIdCol.setCellValueFactory(new PropertyValueFactory<Actor, Integer>("id"));
            actorFirstNameCol.setCellValueFactory(new PropertyValueFactory<Actor, String>("firstName"));
            actorLastNameCol.setCellValueFactory(new PropertyValueFactory<Actor, String>("lastName"));
            actorTbl.setItems(manager.getAllActors());

        }
    }

    @FXML
    void deleteMouseClick(MouseEvent event) {

        int i = tabpane.getSelectionModel().getSelectedIndex();
        switch (i) {

            case 0 -> {
                Customer customer = customerTbl.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    manager.deleteCustomer(customer.getId());
                }

            }
            case 1 -> {
                Rental rental = rentalTbl.getSelectionModel().getSelectedItem();
                if (rental != null) {
                    manager.deleteRental(rental.getId());
                }
            }
            case 2 -> {
                Film film = filmTbl.getSelectionModel().getSelectedItem();
                if (film != null) {
                    manager.deleteFilm(film.getId());
                }
            }
            case 3 -> {
                Staff staff = staffTbl.getSelectionModel().getSelectedItem();
                if (staff !=  null){
                    manager.deleteStaff(staff.getId());
                }
            }


        }
    }

    @FXML
    void updateMouseClick(MouseEvent event) throws IOException {
        int i = tabpane.getSelectionModel().getSelectedIndex();

        switch (i) {

            case 0 -> {
                Customer customer = customerTbl.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    changeSceneCustomer(event, SceneView.UPDATECUSTOMER, customer);
                }
            }
            case 1 -> {
                Rental rental = rentalTbl.getSelectionModel().getSelectedItem();
                if (rental != null) {
                    changeSceneRental(event, SceneView.UPDATERENTAL, rental);
                }
            }
            case 2 -> {
                Film film = filmTbl.getSelectionModel().getSelectedItem();
                if (film != null) {
                    changeSceneFilm(event, SceneView.UPDATEFILM, film);
                }
            }
            case 3 -> {

            }
        }
    }



    @FXML
    void createMouseClick(MouseEvent event) throws IOException {
        int i = tabpane.getSelectionModel().getSelectedIndex();

        switch (i) {

            case 0 -> {
                changeSceneCustomer(event, SceneView.CREATECUSTOMER, new Customer());
            }
            case 1 -> {
                changeSceneRental(event, SceneView.CREATERENTAL, new Rental());
            }
            case 2 -> {
                changeSceneFilm(event, SceneView.CREATEFILM, new Film());
            }
            case 3 -> {

            }
        }

    }

    @FXML
    void rentmovieMouseClick(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilarentmovie.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}