package com.sakila.controller;

import com.sakila.entity.Address;
import com.sakila.entity.Staff;
import com.sakila.entity.Store;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class StaffController {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Staff staff;
    private SceneView view;


    @FXML
    private ChoiceBox<Address> addressChoice;

    @FXML
    private Button cancelBtn,doneBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastnameTxt;

    @FXML
    private TextField password;

    @FXML
    private ChoiceBox<Store> storeChoice;

    @FXML
    private TextField username;

    @FXML
    void cancelStaff(MouseEvent event) throws IOException {
        if (view.equals(SceneView.CREATESTAFF)) {
            sceneChanger.mainScene(event);
        } else {
            sceneChanger.changeSceneLogin(event);
        }
    }
    @FXML
    void createStaffDone(MouseEvent event) throws IOException {
        Staff staff = new Staff();
        staff.setFirstName(firstnameTxt.getText());
        staff.setLastName(lastnameTxt.getText());
        staff.setAddress(addressChoice.getValue());
        staff.setEmail(emailTxt.getText());
        staff.setStore(storeChoice.getValue());
        staff.setUsername(username.getText());
        staff.setPassword(password.getText());
        staff.setActive(true); //behövs denna?
        manager.createStaff(staff);

        if (view.equals(SceneView.CREATESTAFF)) {
            sceneChanger.mainScene(event);
        } else {
            sceneChanger.changeSceneLogin(event);
        }
    }

    @FXML
    void updateStaffDone(MouseEvent event) throws IOException {

    }
    
        public void initData (SceneView view, Staff staff) {
            addressChoice.setItems(manager.getAllAddresses());
            storeChoice.setItems(manager.getAllStores());
            this.staff = staff;

            switch (view) {
                case CREATESTAFF -> {

                }
                case UPDATESTAFF -> {
                    this.staff = staff;
                    Address address = manager.readAddress(staff.getAddress().getId());
                    Store store = manager.readStore(staff.getStore().getId());
                    firstnameTxt.setText(staff.getFirstName());
                    lastnameTxt.setText(staff.getLastName());
                    emailTxt.setText(staff.getEmail());
                    username.setText(staff.getUsername());
                    password.setText(staff.getPassword());
                }
            }

        }

    }


