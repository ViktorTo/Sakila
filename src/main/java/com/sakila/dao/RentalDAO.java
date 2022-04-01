package com.sakila.dao;

import com.sakila.entity.Rental;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class RentalDAO implements DatabaseAccessObject<Rental> {
    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Rental data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Rental read(int id) {
        Session session = databaseSession.startSession();
        Rental rental = session.find(Rental.class, id);
        databaseSession.endSession(session);
        return null;
    }

    @Override
    public void update(Rental data) {
        Session session = databaseSession.startSession();
        Rental rental = session.find(Rental.class, data.getId());
        rental.setRentalDate(data.getRentalDate());
        rental.setInventory(data.getInventory());
        rental.setCustomer(data.getCustomer());
        rental.setReturnDate(data.getReturnDate());
        rental.setStaff(data.getStaff());
        rental.setLastUpdate(data.getLastUpdate());
        rental.setPayments(data.getPayments());
        databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Rental rental = session.find(Rental.class, id);
        session.remove(rental);
        databaseSession.endSession(session);
    }

    @Override
    public List<Rental> readAll() {
        Session session = databaseSession.startSession();
        List<Rental> rentalList = session.createQuery("FROM Rental ", Rental.class).getResultList();
        databaseSession.endSession(session);
        return null;
    }
}