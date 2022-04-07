package com.sakila.controller;

import com.sakila.entity.*;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    public void initFilm(int id) {
        film = manager.getFilmById(id);
        filmTitleLabel.setText(film.getTitle());
        filmDescriptionLabel.setText(film.getDescription());
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
        filmInventoryList.setItems(FXCollections.observableArrayList(manager.getInventoryByFilm(id)));
    }
}
