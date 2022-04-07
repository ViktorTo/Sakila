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
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Staff staff;
    private Film filmSaved;
    private int clickCount = 0;

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

    @FXML
    private TableColumn<Inventory, Film> inventoryFilmCol;

    @FXML
    private TableColumn<Inventory, Integer> inventoryIdCol;

    @FXML
    private TableColumn<Inventory, Timestamp> inventoryLastUpdateCol;

    @FXML
    private TableColumn<Inventory, Store> inventoryStoreIdCol;

    @FXML
    private Tab inventoryTab;

    @FXML
    private TableView<Inventory> inventoryTbl;

    @FXML
    private Circle staffImageView;


    public void changeToFilmTab() {
        //Show data in film tab
        if (filmTab.isSelected()) {
            filmTbl.getItems().clear();
            filmidCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
            titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
            lengthCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
            releaseCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("releaseYear"));
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

    public void changeToInventoryTab() {
    //Show data in inventory tab
        if (inventoryTab.isSelected()) {
            inventoryTbl.getItems().clear();
            inventoryIdCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("id"));
            inventoryFilmCol.setCellValueFactory(new PropertyValueFactory<Inventory, Film>("film"));
            inventoryStoreIdCol.setCellValueFactory(new PropertyValueFactory<Inventory, Store>("store"));
            inventoryLastUpdateCol.setCellValueFactory(new PropertyValueFactory<Inventory, Timestamp>("lastUpdate"));
            inventoryTbl.setItems(manager.getAllInventories());
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
                    changeToCustomerTab();
                }

            }
            case 1 -> {
                Rental rental = rentalTbl.getSelectionModel().getSelectedItem();
                if (rental != null) {
                    manager.deleteRental(rental.getId());
                    changeToRentalTab();
                }
            }
            case 2 -> {
                Film film = filmTbl.getSelectionModel().getSelectedItem();
                if (film != null) {
                    manager.deleteFilm(film.getId());
                    changeToFilmTab();
                }
            }
            case 3 -> {
                Staff staff = staffTbl.getSelectionModel().getSelectedItem();
                if (staff !=  null){
                    manager.deleteStaff(staff.getId());
                    changeToStaffTab();
                }
            }
            case 4 -> {
                Actor actor = actorTbl.getSelectionModel().getSelectedItem();
                if (actor != null){
                    manager.deleteActor(actor.getId());
                    changeToActorTab();
                }

            }
            case 5 -> {
                Inventory inventory = inventoryTbl.getSelectionModel().getSelectedItem();
                if (inventory != null){
                    manager.deleteInventory(inventory.getId());
                    changeToInventoryTab();
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
                    sceneChanger.customerScene(event, SceneView.UPDATECUSTOMER, customer);
                }
            }
            case 1 -> {
                Rental rental = rentalTbl.getSelectionModel().getSelectedItem();
                if (rental != null) {
                    sceneChanger.changeSceneRental(event, SceneView.UPDATERENTAL, rental);
                }
            }
            case 2 -> {
                Film film = filmTbl.getSelectionModel().getSelectedItem();
                if (film != null) {
                    sceneChanger.changeSceneFilm(event, SceneView.UPDATEFILM, film);
                }
            }
            case 3 -> {


            }
            case 4 -> {
                //HÄR
                Actor actor = actorTbl.getSelectionModel().getSelectedItem();
                if(actor != null) {
                    sceneChanger.changeSceneActor(event, SceneView.UPDATEACTOR, actor);
                }

            }
            case 5 -> {
                Inventory inventory = inventoryTbl.getSelectionModel().getSelectedItem();
                if (inventory != null) {
                    sceneChanger.changeSceneInventory(event, SceneView.UPDATEINVENTORY, inventory);
                }
            }
        }
    }


    @FXML
    void createMouseClick(MouseEvent event) throws IOException {
        int i = tabpane.getSelectionModel().getSelectedIndex();

        switch (i) {

            case 0 -> {
                sceneChanger.customerScene(event, SceneView.CREATECUSTOMER, new Customer());
            }
            case 1 -> {
                Rental rental = new Rental();
                rental.setStaff(this.staff);
                sceneChanger.changeSceneRental(event, SceneView.CREATERENTAL, rental);
            }
            case 2 -> {
                sceneChanger.changeSceneFilm(event, SceneView.CREATEFILM, new Film());
            }
            case 3 -> {

            }
            case 4 -> {
             sceneChanger.changeSceneActor(event, SceneView.CREATEACTOR, new Actor());

            }
            case 5 -> {
                sceneChanger.changeSceneInventory(event, SceneView.CREATEINVENTORY, new Inventory());
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

    public void initData(Staff staff) throws IOException {
        this.staff = staff;
        if(staff.getPicture() != null) {
            ImagePattern image = new ImagePattern(getImage(staff.getPicture()));
            staffImageView.setFill(image);
            staffImageView.setVisible(true);
        }
    }

    public void initialize() throws IOException {
        if(staff == null) {
            staff = manager.getStaffByUsername("Mike");
            initData(staff);
        }
    }

    @FXML
    public void filmTblClicked(MouseEvent event) throws IOException {
        clickCount++;
        Film film = filmTbl.getSelectionModel().getSelectedItem();
        if(film == filmSaved) {
            if (clickCount == 2 && film != null) {
                sceneChanger.informationScene(event, film.getId());
                clickCount = 0;
            }
        }else {
            clickCount = 1;
            filmSaved = film;
        }
    }

    private Image getImage(byte[] picture) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(picture);
        return new Image(bis);
    }

}