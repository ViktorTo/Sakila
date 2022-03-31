package com.sakila.dao;

import com.sakila.entity.Address;
import com.sakila.entity.FilmText;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class FilmTextDAO implements DatabaseAccessObject<FilmText>{
    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(FilmText data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public FilmText read(int id) {
        Session session = databaseSession.startSession();
        FilmText filmText = session.find(FilmText.class, id);
        databaseSession.endSession(session);
        return null;
    }

    @Override
    public void update(FilmText data) {
        Session session = databaseSession.startSession();
        FilmText filmText = session.find(FilmText.class, data.getId());
        filmText.setDescription(data.getDescription());
        filmText.setTitle(data.getTitle());
        databaseSession.endSession(session);

    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        FilmText filmText = session.find(FilmText.class, id);
        session.remove(filmText);
        databaseSession.endSession(session);

    }

    @Override
    public List<FilmText> readAll() {
        Session session = databaseSession.startSession();
        List<FilmText> filmTextList =  session.createQuery("FROM Film_Text ", FilmText.class).getResultList();
        databaseSession.endSession(session);
        return filmTextList;
    }
}
