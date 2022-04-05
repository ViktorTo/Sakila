package com.sakila.controller;

import com.sakila.dao.CustomerDAO;
import com.sakila.entity.Customer;
import com.sakila.entity.Film;
import com.sakila.logic.Manager;
import com.sakila.main.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RentController {

    private final Manager manager = new Manager();
    private Customer customer;

    @FXML
    private TextField emailField, searchField;

    @FXML
    private ListView<Film> filmList;

    @FXML
    private VBox loginBox, rentBox;

    @FXML
    private Label noEmailLabel;

    @FXML
    void cancelClicked(MouseEvent event) throws IOException {
        changeScene(event);
    }

    @FXML
    void loginClicked(MouseEvent event) {

        customer = manager.searchedEmail(emailField.getText());
        if (customer!=null){
            noEmailLabel.setVisible(false);
            loginBox.setVisible(false);
            rentBox.setVisible(true);
            filmList.setItems(manager.getAllFilms());
        } else {
            noEmailLabel.setVisible(true);
        }
    }

    @FXML
    void registerClicked(MouseEvent event) {

    }

    @FXML
    void rentClicked(MouseEvent event) {

    }

    @FXML
    void searchTyped(KeyEvent event) {
        if (!searchField.getText().isEmpty() || searchField.getText() != null) {
            ObservableList<Film> searchedFilm = manager.searchedFilm(searchField.getText());
            filmList.setItems(searchedFilm);
        }
    }

    public void changeScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilamain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
