package com.sakila.logic;

import com.sakila.dao.*;
import com.sakila.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Manager {

    private final ActorDAO actorDAO = new ActorDAO();
    private final AddressDAO addressDAO = new AddressDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final StoreDAO storeDAO = new StoreDAO();
    private final FilmDAO filmDAO = new FilmDAO();

    public ObservableList<Actor> getAllActors() {
        return FXCollections.observableArrayList(actorDAO.readAll());
    }

    public ObservableList<Customer> getAllCustomers() {
        return FXCollections.observableArrayList(customerDAO.readAll());
    }

    public void deleteCustomer(int id){
        Customer customer = customerDAO.read(id);
        if (customer != null) {
            customerDAO.delete(id);
        }
    }

    public void createCustomer(Customer customer) {
        if(customer != null) {
            customerDAO.create(customer);
        }
    }

    public ObservableList<Film> getAllFilms() {
        return FXCollections.observableArrayList(filmDAO.readAll());
    }

    public void deleteFilm(int id) {
        Film film = filmDAO.read(id);
        if (film != null) {
            filmDAO.delete(id);
        }
    }

    public ObservableList<Address> getAllAddresses() {
        return FXCollections.observableArrayList(addressDAO.readAll());
    }

    public ObservableList<Store> getAllStores() {
        return FXCollections.observableArrayList(storeDAO.readAll());
    }


}
