package com.sakila.controller;

import com.sakila.entity.Inventory;
import com.sakila.utility.SceneView;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class InventoryController {

    private Inventory inventory;

    @FXML
    private ListView<?> filmListView;

    @FXML
    private ListView<?> inventoryListView;

    @FXML
    private ChoiceBox<?> storeIdChoice;

    @FXML
    void addToInventoryClicked(MouseEvent event) {

    }

    @FXML
    void cancelClicked(MouseEvent event) {

    }

    @FXML
    void inventoryDoneClicked(MouseEvent event) {

    }

    @FXML
    void updateInventoryClicked(MouseEvent event) {

    }

    @FXML
    void removeFromInventoryClicked(MouseEvent event) {

    }

    public void initData(SceneView view, Inventory inventory) {
        switch (view){
            case CREATEINVENTORY -> {

            }
            case UPDATEINVENTORY -> {
                this.inventory = inventory;

            }
        }
    }
}
