package com.sakila.controller;

import com.sakila.entity.*;
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
    private ChoiceBox<Country> countryChoice;

    @FXML
    private TextField firstnameTxt, lastnameTxt, emailTxt, streetAddressTxt, postalCodeTxt, cityTxt, districtTxt;

    @FXML
    private ChoiceBox<Store> storeChoice;

    @FXML
    public void createCustomerDone(MouseEvent event) throws IOException {

        City city = manager.readCityByName(cityTxt.getText());
        if (city == null) {
            city = new City();
            city.setCity(cityTxt.getText());
            city.setCountry(countryChoice.getValue());
            manager.createCity(city);
        }
        Address address = manager.readAddressByName(streetAddressTxt.getText());
        if (address == null) {
            address = new Address();
            address.setAddress(streetAddressTxt.getText());
            address.setDistrict(districtTxt.getText());
            address.setCity(city);
            address.setPostalCode(postalCodeTxt.getText());
            address.setPhone(" ");
            manager.createAddress(address);
        }

        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setStore(storeChoice.getValue());
        customer.setFirstName(firstnameTxt.getText());
        customer.setLastName(lastnameTxt.getText());
        customer.setEmail(emailTxt.getText());
        customer.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setActive(true);
        manager.createCustomer(customer);

        if(view.equals(SceneView.CREATECUSTOMER)) {
            sceneChanger.mainScene(event);
        } else {
        sceneChanger.changeSceneLogin(event);
        }
    }
    @FXML
    public void updateCustomerDone(MouseEvent event) throws IOException {

        City city = manager.readCityByName(cityTxt.getText());
        Address address = manager.readAddressByName(streetAddressTxt.getText());
        boolean changeCountry = false;

        if (city == null) {
            city = new City();
            city.setCity(cityTxt.getText());
            city.setCountry(countryChoice.getValue());
            manager.createCity(city);

        } else if (city.getCountry() != countryChoice.getValue()) {
            changeCountry = true;
            city = new City();
            city.setCity(cityTxt.getText());
            city.setCountry(countryChoice.getValue());
            manager.createCity(city);
        }

        if (address == null || changeCountry) {
            address = new Address();
            address.setAddress(streetAddressTxt.getText());
            address.setDistrict(districtTxt.getText());
            address.setCity(city);
            address.setPostalCode(postalCodeTxt.getText());
            address.setPhone(" ");
            System.out.println("hej");
            manager.createAddress(address);
        }

        customer.setFirstName(firstnameTxt.getText());
        customer.setLastName(lastnameTxt.getText());
        customer.setEmail(emailTxt.getText());
        customer.setStore(storeChoice.getValue());
        customer.setAddress(address);


        manager.updateCustomer(customer);
        sceneChanger.mainScene(event);
    }
    @FXML
    public void cancelCustomer(MouseEvent event) throws IOException {
        if(view.equals(SceneView.CREATECUSTOMER) || view.equals(SceneView.UPDATECUSTOMER)) {
            sceneChanger.mainScene(event);
        }else {
            sceneChanger.changeSceneLogin(event);
        }
    }

    public void initData(SceneView view, Customer customer) {
        countryChoice.setItems(manager.getAllCountries());
        storeChoice.setItems(manager.getAllStores());
        this.customer = customer;
        this.view = view;

        if (view == SceneView.UPDATECUSTOMER) {
            Address address = manager.readAddress(customer.getAddress().getId());
            City city = manager.readCity(address.getCity().getId());
            Country country = manager.readCountry(city.getCountry().getId());
            Store store = manager.readStore(customer.getStore().getId());
            firstnameTxt.setText(customer.getFirstName());
            lastnameTxt.setText(customer.getLastName());
            emailTxt.setText(customer.getEmail());
            storeChoice.getSelectionModel().select(store);
            streetAddressTxt.setText(address.getAddress());
            districtTxt.setText(address.getDistrict());
            cityTxt.setText(city.getCity());
            countryChoice.getSelectionModel().select(country);
            postalCodeTxt.setText(address.getPostalCode());
        }
    }
}
