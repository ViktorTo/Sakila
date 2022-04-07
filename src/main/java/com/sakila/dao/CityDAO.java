package com.sakila.dao;

import com.sakila.entity.City;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class CityDAO implements DatabaseAccessObject<City>{
    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(City data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public City read(int id) {
        Session session = databaseSession.startSession();
        City city = session.find(City.class, id);
        databaseSession.endSession(session);
        return city;
    }

    @Override
    public void update(City data) {
        Session session = databaseSession.startSession();
        City city = session.find(City.class, data.getId());
        city.setCity(data.getCity());
        city.setAddresses(data.getAddresses());
        city.setCountry(data.getCountry());
        databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        City city = session.find(City.class, id);
        session.remove(city);
        databaseSession.endSession(session);

    }

    public City readCityByName(String cityName) {
        Session session = databaseSession.startSession();
        List<City> cityList = session.createQuery("FROM City c WHERE c.city = :name")
                        .setParameter("name", cityName)
                                .getResultList();
        databaseSession.endSession(session);
        if (cityList.size() > 0) {
            return cityList.get(0);
        }
        return null;
    }

    @Override
    public List<City> readAll() {
        Session session = databaseSession.startSession();
        List<City> cityList = session.createQuery("FROM City ", City.class). getResultList();
        databaseSession.endSession(session);
        return cityList;
    }
}
