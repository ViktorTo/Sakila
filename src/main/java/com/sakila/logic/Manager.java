package com.sakila.logic;

import com.sakila.dao.*;
import com.sakila.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private final ActorDAO actorDAO = new ActorDAO();
    private final AddressDAO addressDAO = new AddressDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final StoreDAO storeDAO = new StoreDAO();
    private final FilmDAO filmDAO = new FilmDAO();
    private final LanguageDAO languageDAO = new LanguageDAO();
    private final StaffDAO staffDAO = new StaffDAO();
    private final RentalDAO rentalDAO = new RentalDAO();
    private final InventoryDAO inventoryDAO = new InventoryDAO();

    public ObservableList<Actor> getAllActors() {
        return FXCollections.observableArrayList(actorDAO.readAll());
    }

    public ObservableList<Customer> getAllCustomers() {
        return FXCollections.observableArrayList(customerDAO.readAll());
    }

    public ObservableList<Staff> getAllStaff() {
        return FXCollections.observableArrayList(staffDAO.readAll());
    }

    public void deleteCustomer(int id) {
        Customer customer = customerDAO.read(id);
        if (customer != null) {
            customerDAO.delete(id);
        }
    }

    public void createCustomer(Customer customer) {
        if (customer != null) {
            customerDAO.create(customer);
        }
    }

    public void updateCustomer(Customer customer) {
        if (customer.getId() != 0) {
            customerDAO.update(customer);
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

    public void deleteRental(int id) {
        Rental rental = rentalDAO.read(id);
        if (rental != null) {
            rentalDAO.delete(id);
        }
    }
    //Här
    public void deleteStaff(int id){
        Staff staff = staffDAO.read(id);
        if(staff != null){
            staffDAO.delete(id);
        }
    }

    public void createFilm(Film film) {
        if (film != null) {
            filmDAO.create(film);
        }
    }

    public void updateFilm(Film film) {
        if (film != null) {
            filmDAO.update(film);
        }
    }

    public ObservableList<Address> getAllAddresses() {
        return FXCollections.observableArrayList(addressDAO.readAll());
    }

    public Address readAddress(int id) {
        if (id != 0) {
            return addressDAO.read(id);
        }
        return null;
    }

    public ObservableList<Store> getAllStores() {
        return FXCollections.observableArrayList(storeDAO.readAll());
    }

    public ObservableList<Language> getAllLanguages() { return FXCollections.observableArrayList(languageDAO.readAll()); }

    public ObservableList<Rental> getAllRentals() { return FXCollections.observableArrayList(rentalDAO.readAll()); }

    public ObservableList<Rental> getRentalCustomerAndStaff() {return FXCollections.observableArrayList(rentalDAO.readRentalCustomerStaff());}


    public Store readStore(int id) {
        if (id != 0) {
            return storeDAO.read(id);
        }
        return null;
    }

    public Language readLanguage(int id) {
        if (id != 0) {
            return languageDAO.read(id);
        }
        return null;
    }

    public Customer searchedEmail(String email) {
        return customerDAO.readEmail(email);
    }

    public ObservableList<Film> searchedFilm(String filteredText){
        return FXCollections.observableArrayList(filmDAO.readFromSearch(filteredText));
    }

    public ObservableList<Customer> searchedCustomer(String filteredText) {
        return FXCollections.observableArrayList(customerDAO.readFromSearch(filteredText));
    }

    public ObservableList<Film> getFilmsFromInventory(int id) {
        List<Inventory> inventoryList = inventoryDAO.readFromStore(id);
        List<Film> filmList = new ArrayList<>();
        for (Inventory i : inventoryList) {
            if(!filmList.contains(i.getFilm())) {
                filmList.add(i.getFilm());
            }
        }
        return FXCollections.observableArrayList(filmList);
    }

    public Staff getStaffByUsername(String username) {
        return staffDAO.readUsername(username);
    }

    public void updateRental(Rental rental) {
        if (rental != null){
            rentalDAO.update(rental);
        }
    }

    public Inventory getInventoryFromFilm(int filmId) {
        List<Inventory> inventories = inventoryDAO.readByFilm(filmId);
        //TODO: kanske fixa så man inte kan hyra samma film fån inventory.
        return inventories.get(0);
    }

    public void createRental(Rental rental) {
        if (rental != null){
            rentalDAO.create(rental);
        }
    }

    public List<Rental> getFilmsToReturn(Customer customer) {
        List<Rental> rentals = new ArrayList<>();
        for(Rental r : customer.getRentals()) {
            if(r.getReturnDate() == null) {
                rentals.add(r);
            }
        }
        return rentals;
    }

}
