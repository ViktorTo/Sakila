package com.sakila.dao;

import com.sakila.entity.Store;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class StoreDAO implements DatabaseAccessObject<Store>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Store data) {
    Session session = databaseSession.startSession();
    session.persist(data);
    databaseSession.endSession(session);
    }

    @Override
    public Store read(int id) {
        Session session = databaseSession.startSession();
        Store store = session.find(Store.class, id);
        databaseSession.endSession(session);
        return store;
    }

    @Override
    public void update(Store data) {
    Session session = databaseSession.startSession();
    Store store = session.find(Store.class, data.getId());
    store.setAddress(data.getAddress());
    store.setCustomers(data.getCustomers());
    store.setStaff(data.getStaff());
    store.setInventories(data.getInventories());
    store.setManagerStaff(data.getManagerStaff());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
    Session session = databaseSession.startSession();
    Store store = session.find(Store.class, id);
    session.remove(store);
    databaseSession.endSession(session);
    }

    @Override
    public List<Store> readAll() {
        Session session = databaseSession.startSession();
        List<Store> storeList = session.createQuery("FROM Store ", Store.class).getResultList();
        databaseSession.endSession(session);
        return storeList;
    }
}
