package com.sakila.dao;

import com.sakila.entity.Customer;
import com.sakila.entity.Staff;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class StaffDAO implements DatabaseAccessObject<Staff>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Staff data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Staff read(int id) {
        Session session = databaseSession.startSession();
        Staff staff = session.find(Staff.class, id);
        databaseSession.endSession(session);
        return staff;
    }

    @Override
    public void update(Staff data) {
    Session session = databaseSession.startSession();
    Staff staff = session.find(Staff.class, data.getId());
    staff.setAddress(data.getAddress());
    staff.setActive(data.getActive());
    staff.setEmail(data.getEmail());
    staff.setFirstName(data.getFirstName());
    staff.setLastName(data.getLastName());
    staff.setPassword(data.getPassword());
    staff.setPayments(data.getPayments());
    staff.setPicture(data.getPicture());
    staff.setRentals(data.getRentals());
    staff.setStore(data.getStore());
    staff.setUsername(data.getUsername());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
    Session session = databaseSession.startSession();
    Staff staff = session.find(Staff.class, id);
    // session.remove(staff);
    databaseSession.endSession(session);
    }

    @Override
    public List<Staff> readAll() {
        Session session = databaseSession.startSession();
        List<Staff> staffList = session.createQuery("FROM Staff s LEFT JOIN FETCH s.address LEFT JOIN FETCH s.store", Staff.class).getResultList();
        databaseSession.endSession(session);
        return staffList;
    }

    public Staff readUsername(String username) {
        Session session = databaseSession.startSession();
        List<Staff> staffList = session.createQuery("FROM Staff s WHERE s.username = :id", Staff.class)
                .setParameter("id", username)
                .getResultList();
        if(staffList.size() < 1) {
            System.err.println("No found");
            return null;
        }
        return staffList.get(0);
    }
}
