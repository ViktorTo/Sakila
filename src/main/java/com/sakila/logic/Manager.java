package com.sakila.logic;

import com.sakila.dao.ActorDAO;
import com.sakila.dao.AddressDAO;
import com.sakila.dao.CustomerDAO;
import com.sakila.dao.FilmDAO;
import com.sakila.entity.Actor;
import com.sakila.entity.Customer;
import com.sakila.entity.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Manager {

    private final ActorDAO actorDAO = new ActorDAO();
    private final AddressDAO addressDAO = new AddressDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
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

    public ObservableList<Film> getAllFilms() {
        return FXCollections.observableArrayList(filmDAO.readAll());
    }

    public void deleteFilm(int id) {
        Film film = filmDAO.read(id);
        if (film != null) {
            filmDAO.delete(id);
        }
    }

}
