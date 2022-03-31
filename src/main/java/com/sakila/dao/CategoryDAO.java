package com.sakila.dao;

import com.sakila.entity.Address;
import com.sakila.entity.Category;
import com.sakila.utility.DatabaseSession;
import org.hibernate.Session;

import java.util.List;

public class CategoryDAO implements DatabaseAccessObject <Category>{

    private final DatabaseSession databaseSession = new DatabaseSession();

    @Override
    public void create(Category data) {
        Session session = databaseSession.startSession();
        session.persist(data);
        databaseSession.endSession(session);
    }

    @Override
    public Category read(int id) {
        Session session = databaseSession.startSession();
        Category category = session.find(Category.class , id);
        databaseSession.endSession(session);
        return category;
    }

    @Override
    public void update(Category data) {
        Session session = databaseSession.startSession();
        Category category = session.find(Category.class, data.getId());
        category.setName(data.getName());
        category.setFilmCategories(data.getFilmCategories());
        databaseSession.endSession(session);

    }

    @Override
    public void delete(int id) {
        Session session = databaseSession.startSession();
        Category category = session.find(Category.class, id);
        session.remove(category);
        databaseSession.endSession(session);
    }

    @Override
    public List<Category> readAll() {
        Session session = databaseSession.startSession();
        List<Category> categoryList = session.createQuery("FROM Category ", Category.class).getResultList();
        databaseSession.endSession(session);
        return null;
    }
}
