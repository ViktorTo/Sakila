package com.sakila.controller;

import com.sakila.entity.Address;
import com.sakila.entity.Customer;
import com.sakila.entity.Store;
import com.sakila.logic.Manager;
import com.sakila.main.Main;
import com.sakila.utility.SceneView;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerController {

    private final Manager manager = new Manager();
    private Customer customer;

    @FXML
    private ChoiceBox<Address> addressChoice;

    @FXML
    private Button cancelBtn, doneBtn;

    @FXML
    private TextField firstnameTxt, lastnameTxt, emailTxt;

    @FXML
    private ChoiceBox<Store> storeChoice;

    @FXML
    public void createCustomerDone(MouseEvent event) throws IOException {
        Customer customer = new Customer();
        customer.setAddress(addressChoice.getValue());
        customer.setStore(storeChoice.getValue());
        customer.setFirstName(firstnameTxt.getText());
        customer.setLastName(lastnameTxt.getText());
        customer.setEmail(emailTxt.getText());
        customer.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setActive(true);
        manager.createCustomer(customer);
        changeScene(event);
    }
    @FXML
    public void updateCustomerDone(MouseEvent event) throws IOException {
        customer.setFirstName(firstnameTxt.getText());
        customer.setLastName(lastnameTxt.getText());
        customer.setEmail(emailTxt.getText());
        customer.setAddress(addressChoice.getValue());
        customer.setStore(storeChoice.getValue());
        manager.updateCustomer(customer);
        changeScene(event);
    }
    @FXML
    public void cancelCustomer(MouseEvent event) throws IOException {
        changeScene(event);
    }

    public void changeScene(MouseEvent event) throws  IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilamain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initData(SceneView view, Customer customer) {

        addressChoice.setItems(manager.getAllAddresses());
        storeChoice.setItems(manager.getAllStores());

        switch (view) {
            case CREATECUSTOMER -> {
             this.customer = customer;
            }
            case UPDATECUSTOMER -> {
                this.customer = customer;
                firstnameTxt.setText(customer.getFirstName());
                lastnameTxt.setText(customer.getLastName());
                emailTxt.setText(customer.getEmail());
//                addressChoice
//                storeChoice.getSelectionModel().select(customer.getStore());
            }
        }

    }

    public void initialize() {
        
//        storeChoice.getSelectionModel().selectedIndexProperty().addListener(e -> {
//            System.out.println("Hej");
//        });
    }

}
