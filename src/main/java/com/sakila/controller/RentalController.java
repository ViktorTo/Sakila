package com.sakila.controller;

import com.sakila.dao.CustomerDAO;
import com.sakila.entity.Customer;
import com.sakila.entity.Film;
import com.sakila.entity.Inventory;
import com.sakila.entity.Rental;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.type.LocalDateTimeType;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RentalController {
    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Rental rental;
    @FXML
    private TextField customerEmailTxt;

    @FXML
    private ListView<Customer> customerListView;

    @FXML
    private ListView<Film> filmListView;

    @FXML
    private TextField filmTitleTxt;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    public void cancelClicked(MouseEvent event) throws IOException {
        sceneChanger.mainScene(event);
    }

    @FXML
    public void updateRentalClicked(MouseEvent event) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.of(returnDatePicker.getValue(), LocalTime.now());
        rental.setReturnDate(Timestamp.valueOf(localDateTime));
        manager.updateRental(rental);
        sceneChanger.mainScene(event);
    }

    @FXML
    public void newCustomerClicked(MouseEvent event) throws IOException{
        sceneChanger.customerScene(event, SceneView.CREATECUSTOMER, new Customer());
    }

    @FXML
    public void rentMovieClicked(MouseEvent event) {
        Rental rental = new Rental();
        Timestamp rentaldate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp returndate = Timestamp.valueOf(LocalDateTime.now().plusDays(7));

        rental.setRentalDate(rentaldate);
//        rental.setInventory(filmListView.getSelectionModel().getSelectedItem().getId());
        rental.setCustomer(customerListView.getSelectionModel().getSelectedItem());
        rental.setReturnDate(returndate);
//        rental.setStaff(); Todo staff???
        rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

    }

    @FXML
    public void searchCustomerTyped(KeyEvent event) {
        ObservableList<Customer> searchedCustomer = manager.searchedCustomer(customerEmailTxt.getText());
        customerListView.setItems(searchedCustomer);
    }

    @FXML
    public void searchMovieTyped(KeyEvent event) {
        ObservableList<Film> searchedFilm = manager.searchedFilm(filmTitleTxt.getText());
        filmListView.setItems(searchedFilm);
    }


    public void initData(SceneView view, Rental rental) {

        switch (view) {
            case CREATERENTAL -> {
            customerListView.setItems(manager.getAllCustomers());

            }
            case UPDATERENTAL -> {
                this.rental = rental;
                returnDatePicker.setValue(rental.getReturnDate().toLocalDateTime().toLocalDate());
            }
        }
    }
    @FXML
    public void chooseCustomerClicked(MouseEvent event) {
        Customer customer = customerListView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            filmListView.setItems(manager.getFilmsFromInventory(customer.getStore().getId()));
        }
    }
}
