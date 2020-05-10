package com.codecool.books.model;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJDBC implements Dao<Author> {
    private DataSource dataSource;

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Author author) {
        String query;
        query = String.format("INSERT INTO author (first_name, last_name, birth_date) VALUES ('%s', '%s', '%s'j);",
                author.getFirstName(), author.getLastName(), author.getBirthDate());
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.execute(query);
            statement.close();
            dataSource.getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
       String query = String.format("UPDATE author SET " +
               "(first_name, last_name, birth_date) = ('%s','%s', '%s') WHERE author.id = '%d'",
               author.getFirstName(), author.getLastName(), author.getBirthDate(), author.getId());
       try {
           Statement statement = dataSource.getConnection().createStatement();
           statement.execute(query);
           statement.close();
           dataSource.getConnection().close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public Author get(int id) {
        String query = String.format("SELECT * FROM author WHERE author.id = '%d';", id);
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Author authorFromResultSet = null;
            while (resultSet.next()) {
                authorFromResultSet = new Author(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date"));
                authorFromResultSet.setId(resultSet.getInt("id"));
            }
            dataSource.getConnection().close();
            return authorFromResultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Author> getAll() {
        String query = String.format("SELECT * FROM author;");
        List<Author> authorsFromQuery = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
               Author authorFromResultSet = new Author(
                       resultSet.getString("first_name"),
                       resultSet.getString("last_name"),
                       resultSet.getDate("birth_date"));
               authorFromResultSet.setId(resultSet.getInt("id"));
               authorsFromQuery.add(authorFromResultSet);
            }
            dataSource.getConnection().close();
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorsFromQuery;
    }
}
