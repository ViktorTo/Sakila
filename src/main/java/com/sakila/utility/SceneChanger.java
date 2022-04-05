package com.sakila.utility;

import com.sakila.controller.CustomerController;
import com.sakila.entity.Customer;
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

    public void customerScene(MouseEvent event, SceneView view, Customer customer) throws IOException {
        String fxml = "";
        switch (view) {
            case CREATECUSTOMER -> {
                fxml = "sakilacreatecustomer.fxml";
            }
            case UPDATECUSTOMER -> {
                fxml = "sakilaupdatecustomer.fxml";
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


}
