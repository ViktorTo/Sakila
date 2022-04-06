package com.sakila.utility;

import com.sakila.controller.*;
import com.sakila.entity.*;
import com.sakila.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public void mainScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilamain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mainScene(MouseEvent event, Staff staff) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilamain.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initData(staff);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void customerScene(MouseEvent event, SceneView view, Customer customer) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATECUSTOMER -> {
                fxml = "sakilacreatecustomer.fxml";
            }
            case UPDATECUSTOMER -> {
                fxml = "sakilaupdatecustomer.fxml";
            }
            case CREATECUSTOMERUSER -> {
                fxml = "sakilacreatecustomer.fxml";
            }
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        CustomerController controller = loader.getController();
        controller.initData(view, customer);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneRental(MouseEvent event, SceneView view, Rental rental) throws IOException {
        String fxml = "";
        switch(view) {
            case CREATERENTAL -> {
                fxml = "sakilacreaterental.fxml";
            }
            case UPDATERENTAL -> {
                fxml = "sakilaupdaterental.fxml";
            }

        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        RentalController controller = loader.getController();
        controller.initData(view, rental);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneFilm(MouseEvent event, SceneView view, Film film) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATEFILM -> {
                fxml = "sakilacreatefilm.fxml";
            }
            case UPDATEFILM -> {
                fxml = "sakilaupdatefilm.fxml";
            }
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        FilmController controller = loader.getController();
        controller.initData(view, film);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneInventory(MouseEvent event, SceneView view, Inventory inventory) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATEINVENTORY -> {
                fxml = "sakilacreateinventory.fxml";
            }
            case UPDATEINVENTORY -> {
                fxml = "sakilaupdateinventory.fxml";
            }
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();
        InventoryController controller = loader.getController();
        controller.initData(view, inventory);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sakilarentmovie.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
