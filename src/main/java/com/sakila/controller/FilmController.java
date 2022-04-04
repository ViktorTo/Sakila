package com.sakila.controller;

import com.sakila.dao.FilmDAO;
import com.sakila.entity.Film;
import com.sakila.entity.Language;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class FilmController implements Initializable {
    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Film film;
    @FXML
    private Button cancelBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private ChoiceBox<Language> languageidChoice;

    @FXML
    private TextField filmlengthTxt;

    @FXML
    private TextField rentaldurTxt;

    @FXML
    private TextField rentalrateTxt;

    @FXML
    private TextField replacementTxt;

    @FXML
    private TextField titleTxt;


    @FXML
    public void updateFilmDone(MouseEvent event) throws IOException {

        film.setTitle(titleTxt.getText());
        film.setRentalDuration(Integer.parseInt(rentaldurTxt.getText()));
        film.setRentalRate(BigDecimal.valueOf(Double.parseDouble(rentalrateTxt.getText())));
        film.setLength(Integer.parseInt(filmlengthTxt.getText()));
        film.setReplacementCost(BigDecimal.valueOf(Double.parseDouble(replacementTxt.getText())));
        film.setLanguage(languageidChoice.getValue());
        manager.updateFilm(film);
        sceneChanger.mainScene(event);
    }
    @FXML
    public void createFilmDone(MouseEvent event) throws IOException {
        Film film = new Film();
        film.setTitle(titleTxt.getText());
        film.setRentalDuration(Integer.parseInt(rentaldurTxt.getText()));
        film.setRentalRate(BigDecimal.valueOf(Double.parseDouble(rentalrateTxt.getText())));
        film.setLength(Integer.parseInt(filmlengthTxt.getText()));
        film.setReplacementCost(BigDecimal.valueOf(Double.parseDouble(replacementTxt.getText())));
        film.setLanguage(languageidChoice.getValue());
        film.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        manager.createFilm(film);
        sceneChanger.mainScene(event);
    }
    @FXML
    public void cancelFilm(MouseEvent event) throws IOException {
        sceneChanger.mainScene(event);
    }

    public void initData(SceneView view, Film film) {

        switch (view) {
            case CREATEFILM -> {

            }
            case UPDATEFILM -> {
                this.film = film;
                titleTxt.setText(film.getTitle());
                rentaldurTxt.setText(film.getRentalDuration().toString());
                rentalrateTxt.setText(film.getRentalRate().toString());
                filmlengthTxt.setText(film.getLength().toString());
                replacementTxt.setText(film.getReplacementCost().toString());
                Language language = manager.readLanguage(film.getLanguage().getId());
                languageidChoice.getSelectionModel().select(language);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageidChoice.setItems(manager.getAllLanguages());
    }
}
