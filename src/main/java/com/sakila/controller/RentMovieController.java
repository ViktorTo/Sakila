package com.sakila.controller;

import com.sakila.entity.Customer;
import com.sakila.entity.Film;
import com.sakila.entity.Staff;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RentMovieController {

    private final Manager manager = new Manager();
    private Customer customer;
    private Staff staff;
    private final SceneChanger sceneChanger = new SceneChanger();

    @FXML
    private TextField emailField, searchField, staffUserField;

    @FXML
    private ListView<Film> filmList;

    @FXML
    private VBox loginBox, rentBox, staffBox;

    @FXML
    private Label noEmailLabel;

    @FXML
    private Button staffButton;

    @FXML
    private PasswordField staffPasswordField;

    @FXML
    void cancelClicked(MouseEvent event) throws IOException {
        staffButton.setVisible(true);
        changeBox(loginBox);
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
            sceneChanger.mainScene(event);
        }
    }

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
        sceneChanger.customerScene(event, SceneView.CREATECUSTOMER, new Customer());
    }

    @FXML
    void rentClicked(MouseEvent event) {
        Film film = filmList.getSelectionModel().getSelectedItem();
        if(film != null) {

        }
    }

    @FXML
    void staffOnClicked(MouseEvent event) {
        staffButton.setVisible(false);
        changeBox(staffBox);
    }

    @FXML
    void searchTyped(KeyEvent event) {
        if (!searchField.getText().isEmpty() || searchField.getText() != null) {
            ObservableList<Film> searchedFilm = manager.searchedFilm(searchField.getText());
            filmList.setItems(searchedFilm);
        }
    }

    public void initialize() {
        staffButton.setVisible(true);
    }

    public void changeBox(VBox box) {
        loginBox.setVisible(false);
        staffBox.setVisible(false);
        rentBox.setVisible(false);
        staffButton.setVisible(false);

        if(box == loginBox) {
            staffButton.setVisible(true);
        }

        box.setVisible(true);
    }

}
