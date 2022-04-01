package com.sakila.dao;

import com.sakila.entity.Payment;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class PaymentDAO implements DatabaseAccessObject<Payment> {
    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Payment data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Payment read(int id) {
        Session session = databaseSession.startSession();
        Payment payment = session.find(Payment.class, id);
        databaseSession.endSession(session);
        return null;
    }

    @Override
    public void update(Payment data) {
        Session session = databaseSession.startSession();
        Payment payment = session.find(Payment.class, data.getId());
        payment.setCustomer(data.getCustomer());
        payment.setStaff(data.getStaff());
        payment.setRental(data.getRental());
        payment.setAmount(data.getAmount());
        payment.setPaymentDate(data.getPaymentDate());
        payment.setLastUpdate(data.getLastUpdate());
        databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Payment payment = session.find(Payment.class, id);
        session.remove(payment);
        databaseSession.endSession(session);

    }

    @Override
    public List<Payment> readAll() {
        Session session = databaseSession.startSession();
        List<Payment> paymentList = session.createQuery("FROM Payment", Payment.class).getResultList();
        databaseSession.endSession(session);
        return paymentList;
    }
}
