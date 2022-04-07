package com.sakila.dao;

import com.sakila.entity.City;
import com.sakila.entity.Country;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class CountryDAO implements DatabaseAccessObject<Country>{

private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Country data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Country read(int id) {
        Session session = databaseSession.startSession();
        Country country = session.find(Country.class, id);
        databaseSession.endSession(session);
        return country;
    }

    @Override
    public void update(Country data) {
    Session session = databaseSession.startSession();
    Country country = session.find(Country.class, data.getId());
    country.setCountry(data.getCountry());
    country.setCities(data.getCities());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
    Session session = databaseSession.startSession();
    Country country = session.find(Country.class, id);
    session.remove(country);
    databaseSession.endSession(session);
    }

    @Override
    public List<Country> readAll() {
        Session session = databaseSession.startSession();
        List<Country> countryList = session.createQuery("FROM Country" , Country.class).getResultList();
        databaseSession.endSession(session);
        return countryList;
    }

}


