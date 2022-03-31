package com.sakila.dao;

import com.sakila.entity.Customer;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class CustomerDAO implements DatabaseAccessObject<Customer>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Customer data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Customer read(int id) {
        Session session = databaseSession.startSession();
        Customer customer = session.find(Customer.class, id);
        databaseSession.endSession(session);
        return customer;
    }

    @Override
    public void update(Customer data) {
        Session session = databaseSession.startSession();
        Customer customer = session.find(Customer.class, data.getId());
        customer.setAddress(data.getAddress());
        databaseSession.endSession(session);

    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Customer customer = session.find(Customer.class, id);
        session.remove(customer);
        databaseSession.endSession(session);

    }

    @Override
    public List<Customer> readAll() {
        Session session = databaseSession.startSession();
        List<Customer> customerList = session.createQuery("FROM Customer ", Customer.class).getResultList();
        databaseSession.endSession(session);
        return customerList;
    }
}
