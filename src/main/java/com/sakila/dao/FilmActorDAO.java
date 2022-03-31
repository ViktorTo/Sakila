package com.sakila.dao;

import com.sakila.entity.Film;
import com.sakila.entity.FilmActor;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class FilmActorDAO implements DatabaseAccessObject<FilmActor>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(FilmActor data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public FilmActor read(int id) {
        Session session = databaseSession.startSession();
        FilmActor filmActor = session.find(FilmActor.class, id);
        databaseSession.endSession(session);
        return filmActor;
    }

    @Override
    public void update(FilmActor data) {
        Session session = databaseSession.startSession();
        FilmActor filmActor = session.find(FilmActor.class, data.getId());
        filmActor.setActor(data.getActor());
        filmActor.setFilm(data.getFilm());
        databaseSession.endSession(session);

    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        FilmActor filmActor = session.find(FilmActor.class, id);
        session.remove(filmActor);
        databaseSession.endSession(session);

    }

    @Override
    public List<FilmActor> readAll() {
        Session session = databaseSession.startSession();
        List<FilmActor> filmActorList = session.createQuery("FROM Film_Actor ", FilmActor.class).getResultList();
        databaseSession.endSession(session);
        return filmActorList;
    }
}
