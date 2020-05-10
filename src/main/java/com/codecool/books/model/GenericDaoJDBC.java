package com.codecool.books.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
public class GenericDaoJDBC implements Dao<Book> {
    @NonNull private DataSource dataSource;



    @Override
    public void add(Book entity) {

    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public Book get(int id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }
}
