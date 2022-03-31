package com.sakila.dao;

import com.sakila.entity.Film;
import com.sakila.entity.FilmCategory;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class FilmCategoryDAO implements DatabaseAccessObject<FilmCategory>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(FilmCategory data) {
    Session session = databaseSession.startSession();
    session.persist(data);
    databaseSession.endSession(session);
    }

    @Override
    public FilmCategory read(int id) {
        Session session = databaseSession.startSession();
        FilmCategory filmCategory = session.find(FilmCategory.class, id);
        databaseSession.endSession(session);
        return filmCategory;
    }

    @Override
    public void update(FilmCategory data) {
    Session session = databaseSession.startSession();
    FilmCategory filmCategory = session.find(FilmCategory.class, data.getId());
    filmCategory.setCategory(data.getCategory());
    filmCategory.setFilm(data.getFilm());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        FilmCategory filmCategory = session.find(FilmCategory.class, id);
        session.remove(filmCategory);
        databaseSession.endSession(session);
    }

    @Override
    public List<FilmCategory> readAll() {
        Session session = databaseSession.startSession();
        List<FilmCategory> filmCategoryList = session.createQuery("FROM FilmCategory ", FilmCategory.class).getResultList();
        databaseSession.endSession(session);
        return filmCategoryList;
    }
}
