package com.sakila.controller;

import com.sakila.entity.*;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoController {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Film film;
    private List<Actor> actorList = new ArrayList<>();

    @FXML
    private Label customerActiveLabel, customerAddress, customerCreatedLabel, customerEmailLabel,
            customerFnameLabel, customerIdLabel, customerLnameLabel, customerStoreLabel, customerUpdateLabel,
            filmDescriptionLabel, filmIdLabel, filmLanguageLabel,filmRatingLabel, filmSpecialLabel,
            filmTitleLabel, filmUpdateLabel, releaseYearLabel, rentalDurationLabel;

    @FXML
    private VBox customerBox;

    @FXML
    private ListView<Payment> customerPaymentList;

    @FXML
    private ListView<Rental> customerRentalList;

    @FXML
    private ListView<Actor> filmActorList;

    @FXML
    private VBox filmBox;

    @FXML
    private ListView<Inventory> filmInventoryList;

    @FXML
    void backOnClicked(MouseEvent event) throws IOException {
        sceneChanger.mainScene(event);
    }

    @FXML
    void onKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.BACK_SPACE)){
            sceneChanger.mainScene(event);
        }
    }

    public void initFilm(int id) {
        film = manager.getFilmById(id);
        filmTitleLabel.setText(film.getTitle());
        filmDescriptionLabel.setText(getDescription(film));
        releaseYearLabel.setText(film.getReleaseYear().toString());
        filmLanguageLabel.setText(film.getLanguage().toString());
        rentalDurationLabel.setText(film.getRentalDuration().toString());
        filmIdLabel.setText(film.getId().toString());
        filmRatingLabel.setText(film.getRating());
        filmSpecialLabel.setText(film.getSpecialFeatures());
        filmUpdateLabel.setText(film.getLastUpdate().toString());
        for (FilmActor f : manager.getActorsInFilm(film.getId())) {
            actorList.add(f.getActor());
        }
        filmActorList.setItems(FXCollections.observableArrayList(actorList));
        filmInventoryList.setItems(FXCollections.observableArrayList(manager.getInventoryByFilm(film.getId())));
    }

    private String getDescription(Film film) {
        String str = film.getDescription();
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if(i > size/2 && str.charAt(i) == ' ') {
                String temp = str.substring(0, i);
                str = temp + "\n" + str.substring(i, str.length());
                break;
            }
        }
        return str;
    }
}
