package com.codecool.books.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericDaoInMemory implements Dao<Book> {

    Map<Integer, Book> objects = new HashMap<>();
    int idCounter = 0;


    @Override
    public void add(Book entity) {
        entity.setId(idCounter);
        idCounter++;
        objects.put(entity.getId(), entity);

    }

    @Override
    public void update(Book entity) {
        objects.put(entity.getId(), entity);
    }

    @Override
    public Book get(int id) {
       return objects.get(id);
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(objects.values());
    }
}
