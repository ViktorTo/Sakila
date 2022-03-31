package com.sakila.main;

import com.sakila.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {

    @FXML
    private ChoiceBox<?> addressChoice;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastnameTxt;

    @FXML
    private ChoiceBox<?> storeChoice;

    @FXML
    public void createCustomerDone(MouseEvent event) throws IOException {

    }
    @FXML
    public void updateCustomerDone(MouseEvent event) throws IOException {

    }
    @FXML
    public void cancelCustomer(MouseEvent event) throws IOException {
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
