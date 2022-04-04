package com.sakila.controller;

import com.sakila.entity.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class RentController {

    @FXML
    private TextField emailField, searchField;

    @FXML
    private ListView<Film> filmList;

    @FXML
    private VBox loginBox, rentBox;

    @FXML
    private Label noEmailLabel;

    @FXML
    void cancelClicked(MouseEvent event) {

    }

    @FXML
    void loginClicked(MouseEvent event) {

    }

    @FXML
    void registerClicked(MouseEvent event) {

    }

    @FXML
    void rentClicked(MouseEvent event) {

    }

    @FXML
    void searchTyped(KeyEvent event) {

    }

}
