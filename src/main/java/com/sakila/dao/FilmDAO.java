package com.sakila.dao;

import com.sakila.entity.Film;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class FilmDAO implements DatabaseAccessObject<Film> {

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Film data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Film read(int id) {
        Session session = databaseSession.startSession();
        Film film = session.find(Film.class, id);
        databaseSession.endSession(session);
        return film;
    }

    @Override
    public void update(Film data) {
    Session session = databaseSession.startSession();
    Film film = session.find(Film.class, data.getId());
    film.setLanguage(data.getLanguage());
    film.setLength(data.getLength());
    film.setRentalDuration(data.getRentalDuration());
    film.setRentalRate(data.getRentalRate());
    film.setReplacementCost(data.getReplacementCost());
    film.setTitle(data.getTitle());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Film film = session.find(Film.class, id);
        session.remove(film);
        databaseSession.endSession(session);
    }

    @Override
    public List<Film> readAll() {
        Session session = databaseSession.startSession();
        List<Film> filmList = session.createQuery("FROM Film f ORDER BY f.title", Film.class).getResultList();
        databaseSession.endSession(session);
        return filmList;
    }
}
