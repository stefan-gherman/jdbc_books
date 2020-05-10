package com.codecool.books.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Dao<T> {
    /**
     * This method adds a new record in the db
     *
     * @param entity a generic type entity
     */
    void add(T entity);

    /**
     * This method updates a record with new data;
     *
     * @param entity a generic type entity
     */
    void update(T entity);

    /**
     * This method returns one entry from the database
     * Aici, gen, trebuia sa fie si id generic sau ba?
     *
     * @param id the id of the record to get
     * @return T a generic type
     */
    T get(int id);

    /**
     * This method returns all the records in the db
     *
     * @return List<T> a list of all the books in the database
     */
    List<T> getAll();

}
