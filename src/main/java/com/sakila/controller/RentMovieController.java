package com.sakila.controller;

import com.sakila.entity.*;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RentMovieController {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Customer customer;
    private Staff staff;

    @FXML
    private TextField emailField, searchField, staffUserField;

    @FXML
    private ListView<Film> filmList;

    @FXML
    private ListView<Rental> returnListView;

    @FXML
    private VBox loginBox, rentBox, staffBox, returnBox;

    @FXML
    private Label noEmailLabel;

    @FXML
    private Button staffButton, returnButton;

    @FXML
    private PasswordField staffPasswordField;

    @FXML
    void cancelClicked(MouseEvent event) throws IOException {
        staffButton.setVisible(true);
        changeBox(loginBox);
    }

    @FXML
    void backClicked(MouseEvent event) {
        changeBox(rentBox);
        filmList.setItems(manager.getFilmsFromInventory(customer.getStore().getId()));
        returnButton.setText(String.valueOf(manager.getFilmsToReturn(customer).size()));
    }

    @FXML
    void exitClicked(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void loginClicked(MouseEvent event) {
        customer = manager.searchedEmail(emailField.getText());
        if (customer!=null){
            changeBox(rentBox);
            filmList.setItems(manager.getFilmsFromInventory(customer.getStore().getId()));
            returnButton.setText(String.valueOf(manager.getFilmsToReturn(customer).size()));

            if(customer.getStore().getId() == 1) {
                this.staff = manager.getStaffByUsername("Scanner1");
            }else {
                this.staff = manager.getStaffByUsername("Scanner2");
            }

        } else {
            noEmailLabel.setVisible(true);
        }
    }


    @FXML
    void staffLoginClicked(MouseEvent event) throws IOException {
        String staffUsername = staffUserField.getText();
        String staffPassword = staffPasswordField.getText();
        if(staffUsername != null || !staffUsername.isEmpty()) {
            staff = manager.getStaffByUsername(staffUsername);
        }
        if(staff != null) {
            sceneChanger.mainScene(event, staff);
        }
    }

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
        sceneChanger.customerScene(event, SceneView.CREATECUSTOMERUSER, new Customer());
    }

    @FXML
    void rentClicked(MouseEvent event) {
        Film film = filmList.getSelectionModel().getSelectedItem();
        Rental rental = new Rental();

        if(film != null) {
            Timestamp rentaldate = Timestamp.valueOf(LocalDateTime.now());
            rental.setRentalDate(rentaldate);
            rental.setInventory(manager.getInventoryFromFilm(film.getId()));
            rental.setCustomer(customer);
            rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            rental.setStaff(this.staff);
            manager.createRental(rental);
            changeBox(rentBox);
            this.customer = manager.searchedEmail(customer.getEmail());
            returnButton.setText(String.valueOf(manager.getFilmsToReturn(customer).size()));
        }
    }

    @FXML
    void staffOnClicked(MouseEvent event) {
        staffButton.setVisible(false);
        changeBox(staffBox);
    }

    @FXML
    void returnClicked(MouseEvent Event) {
        Rental rental = returnListView.getSelectionModel().getSelectedItem();
        if(rental != null) {
            rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
            manager.updateRental(rental);
            this.customer = manager.searchedEmail(customer.getEmail());
            returnButton.setText(String.valueOf(manager.getFilmsToReturn(customer).size()));
            changeBox(rentBox);

        }
    }

    @FXML
    void searchTyped(KeyEvent event) {
        if (!searchField.getText().isEmpty() || searchField.getText() != null) {
            ObservableList<Film> searchedFilm = manager.searchedFilm(searchField.getText());
            filmList.setItems(searchedFilm);
        }
    }

    @FXML
    void notificationClicked(MouseEvent event) {
        returnListView.setItems(FXCollections.observableArrayList(manager.getFilmsToReturn(customer)));
        changeBox(returnBox);
    }



    public void initialize() {
        staffButton.setVisible(true);
    }

    public void changeBox(VBox box) {
        loginBox.setVisible(false);
        staffBox.setVisible(false);
        rentBox.setVisible(false);
        returnBox.setVisible(false);
        staffButton.setVisible(false);

        if(box == loginBox) {
            staffButton.setVisible(true);
        }
        box.setVisible(true);
    }

}
