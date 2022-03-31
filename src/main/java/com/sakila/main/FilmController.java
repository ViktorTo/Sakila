package com.sakila.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class FilmController {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private ChoiceBox<?> languageidChoice;

    @FXML
    private TextField filmlengthTxt;

    @FXML
    private TextField rentaldurTxt;

    @FXML
    private TextField rentalrateTxt;

    @FXML
    private TextField rentalrateTxt1;

    @FXML
    private TextField replacementTxt;

    @FXML
    private TextField titleTxt;


    @FXML
    public void updateFilmDone(MouseEvent event) throws IOException {

    }
    @FXML
    public void createFilmDone(MouseEvent event) throws IOException {

    }
    @FXML
    public void cancelFilm(MouseEvent event) throws IOException {
        changeScene(event);
    }

    public void changeScene(MouseEvent event) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sakilamain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
