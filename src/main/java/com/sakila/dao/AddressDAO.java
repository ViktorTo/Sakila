package com.sakila.dao;

import com.sakila.entity.Address;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class AddressDAO implements DatabaseAccessObject<Address>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Address data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Address read(int id) {
        Session session = databaseSession.startSession();
        Address address = session.find(Address.class, id);
        databaseSession.endSession(session);
        return address;
    }

    @Override
    public void update(Address data) {
        Session session = databaseSession.startSession();
        Address address = session.find(Address.class, data.getId());
        address.setAddress(data.getAddress());
        address.setAddress2(data.getAddress2());
        address.setCity(data.getCity());
        address.setCustomers(data.getCustomers());
        address.setDistrict(data.getDistrict());
        address.setPhone(data.getPhone());
        address.setPostalCode(data.getPostalCode());
        address.setStaff(data.getStaff());
        address.setStores(data.getStores());
        databaseSession.endSession(session);

    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Address address = session.find(Address.class, id);
        session.remove(address);
        databaseSession.endSession(session);

    }

    @Override
    public List<Address> readAll() {
        Session session = databaseSession.startSession();
        List<Address> addressList = session.createQuery("FROM Address ", Address.class).getResultList();
        databaseSession.endSession(session);
        return addressList;
    }
}
