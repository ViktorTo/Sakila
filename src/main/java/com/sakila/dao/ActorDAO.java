package com.sakila.dao;

import com.sakila.entity.Actor;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class ActorDAO implements DatabaseAccessObject<Actor> {

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Actor data) {

    }

    @Override
    public Actor read(int id) {
        Session session = databaseSession.startSession();
        Actor actor = session.find(Actor.class, id);
        databaseSession.endSession(session);
        return actor;
    }

    @Override
    public void update(Actor data) {
        Session session = databaseSession.startSession();
        Actor actor = session.find(Actor.class, data.getId());
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
    Session session = databaseSession.startSession();
    Actor actor = session.find(Actor.class, id);
    session.delete(actor);
    databaseSession.endSession(session);
    }

    @Override
    public List<Actor> readAll() {
        Session session = databaseSession.startSession();
        List<Actor> actorList = session.createQuery("FROM Actor ", Actor.class).getResultList();
        databaseSession.endSession(session);
        return actorList;
    }

}
