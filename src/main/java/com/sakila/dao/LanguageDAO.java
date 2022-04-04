package com.sakila.dao;

import com.sakila.entity.Language;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class LanguageDAO implements DatabaseAccessObject<Language> {
    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Language data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Language read(int id) {
    Session session = databaseSession.startSession();
    Language language = session.find(Language.class, id);
    databaseSession.endSession(session);
    return language;
    }

    @Override
    public void update(Language data) {
    Session session = databaseSession.startSession();
    Language language = session.find(Language.class, data.getId());
    databaseSession.endSession(session);
    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Language language = session.find(Language.class, id);
        session.remove(language);
        databaseSession.endSession(session);

    }

    @Override
    public List<Language> readAll() {
        Session session = databaseSession.startSession();
        List<Language> languageList = session.createQuery("FROM Language", Language.class).getResultList();
        databaseSession.endSession(session);
        return languageList;
    }
}
