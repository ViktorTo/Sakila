package com.sakila.dao;

import com.sakila.entity.Inventory;
import com.sakila.entity.Language;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class InventoryDAO implements DatabaseAccessObject<Inventory> {
    private final DatabaseSession databaseSession = new DatabaseSession();


    @Override
    public void create(Inventory data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Inventory read(int id) {
        Session session = databaseSession.startSession();
        Inventory inventory = session.find(Inventory.class, id);
        databaseSession.endSession(session);
        return null;
    }

    @Override
    public void update(Inventory data) {
    Session session = databaseSession.startSession();
    Inventory inventory = session.find(Inventory.class, data.getId());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Inventory inventory = session.find(Inventory.class, id);
        session.remove(inventory);
        databaseSession.endSession(session);
    }

    @Override
    public List<Inventory> readAll() {
        Session session = databaseSession.startSession();
        List<Inventory> inventoryList = session.createQuery("FROM Inventory", Inventory.class).getResultList();
        databaseSession.endSession(session);
        return inventoryList;
    }

    public List<Inventory> readFromStore(int id) {
        Session session = databaseSession.startSession();
        List<Inventory> inventoryList = session.createQuery("FROM Inventory i LEFT JOIN FETCH i.film WHERE i.store.id = :id", Inventory.class)
                .setParameter("id", id)
                .getResultList();
        databaseSession.endSession(session);
        return inventoryList;
    }

}
