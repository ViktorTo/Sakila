package com.sakila.dao;

import java.util.List;

public interface DatabaseAccessObject<T> {

    void create(T data);

    T read(int id);

    void update(T data);

    void delete(int id);

    List<T> readAll();

}
