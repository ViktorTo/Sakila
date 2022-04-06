package com.sakila.controller;

import com.sakila.entity.Address;
import com.sakila.entity.Customer;
import com.sakila.entity.Store;
import com.sakila.logic.Manager;
import com.sakila.main.Main;
import com.sakila.utility.SceneChanger;
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
import java.util.List;
import java.util.Objects;

public class CustomerController {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Customer customer;
    private SceneView view;

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

        if(view.equals(SceneView.CREATECUSTOMER)) {
            sceneChanger.mainScene(event);
        }else {
        sceneChanger.changeSceneLogin(event);
        }
    }
    @FXML
    public void updateCustomerDone(MouseEvent event) throws IOException {
        customer.setFirstName(firstnameTxt.getText());
        customer.setLastName(lastnameTxt.getText());
        customer.setEmail(emailTxt.getText());
        customer.setAddress(addressChoice.getValue());
        customer.setStore(storeChoice.getValue());
        manager.updateCustomer(customer);
        sceneChanger.mainScene(event);
    }
    @FXML
    public void cancelCustomer(MouseEvent event) throws IOException {
        if(view.equals(SceneView.CREATECUSTOMER)) {
            sceneChanger.mainScene(event);
        }else {
            sceneChanger.changeSceneLogin(event);
        }
    }

    public void initData(SceneView view, Customer customer) {

        addressChoice.setItems(manager.getAllAddresses());
        storeChoice.setItems(manager.getAllStores());
        this.customer = customer;
        this.view = view;

        if (view == SceneView.UPDATECUSTOMER) {
            firstnameTxt.setText(customer.getFirstName());
            lastnameTxt.setText(customer.getLastName());
            emailTxt.setText(customer.getEmail());
            Address address = manager.readAddress(customer.getAddress().getId());
            Store store = manager.readStore(customer.getStore().getId());
            addressChoice.getSelectionModel().select(address);
            storeChoice.getSelectionModel().select(store);
        }

    }

}
