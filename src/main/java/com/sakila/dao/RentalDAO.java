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
        return rental;
    }


    public Rental readWithInventory(int id) {
        Session session = databaseSession.startSession();
        List<Rental> rentalList = session.createQuery("FROM Rental r LEFT JOIN FETCH r.inventory LEFT JOIN FETCH r.inventory.film WHERE r.id =:id")
                .setParameter("id", id)
                        .getResultList();
        databaseSession.endSession(session);
        if (rentalList.size() > 0){
            return rentalList.get(0);
        }
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
        return rentalList;
    }

    public List<Rental> readRentalCustomerStaff() {
        Session session = databaseSession.startSession();
        List<Rental> rentalList = session.createQuery("SELECT r FROM Rental r LEFT JOIN FETCH r.staff LEFT JOIN FETCH r.customer", Rental.class).getResultList();
        databaseSession.endSession(session);
        return rentalList;
    }
}
