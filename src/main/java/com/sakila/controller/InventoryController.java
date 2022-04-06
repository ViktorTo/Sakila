package com.sakila.controller;

import com.sakila.entity.Film;
import com.sakila.entity.Inventory;
import com.sakila.entity.Store;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Inventory inventory;

    @FXML
    private ListView<Film> filmListView, addedFilmListView;

    @FXML
    private ChoiceBox<Store> storeIdChoice;

    @FXML
    void addToInventoryClicked(MouseEvent event) {
        filmListView.getSelectionModel().getSelectedItem();
        addedFilmListView.setItems(FXCollections.observableArrayList(filmListView.getSelectionModel().getSelectedItem()));

    }

    @FXML
    void cancelClicked(MouseEvent event) throws IOException {
        sceneChanger.mainScene(event);
    }

    @FXML
    void inventoryDoneClicked(MouseEvent event) throws IOException {
        Inventory inventory = new Inventory();
        inventory.setFilm(addedFilmListView.getItems().get(0));
        inventory.setStore(storeIdChoice.getValue());
        inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        manager.createInventory(inventory);
        sceneChanger.mainScene(event);

    }

    @FXML
    void updateInventoryClicked(MouseEvent event) throws IOException {
        inventory.setStore(storeIdChoice.getValue());
        inventory.setFilm(addedFilmListView.getItems().get(0));
        inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        manager.updateInventory(inventory);
        sceneChanger.mainScene(event);
    }

    @FXML
    void removeFromInventoryClicked(MouseEvent event) {
        addedFilmListView.getItems().clear();
    }

    public void initData(SceneView view, Inventory inventory) {
        switch (view){
            case CREATEINVENTORY -> {
                filmListView.setItems(manager.getAllFilms());

            }
            case UPDATEINVENTORY -> {
                this.inventory = inventory;
                filmListView.setItems(manager.getAllFilms());
                addedFilmListView.setItems(FXCollections.observableArrayList(inventory.getFilm()));
                storeIdChoice.setValue(inventory.getStore());

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeIdChoice.setItems(manager.getAllStores());
    }
}
