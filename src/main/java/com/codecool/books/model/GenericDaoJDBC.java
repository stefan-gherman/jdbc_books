package com.codecool.books.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GenericDaoJDBC implements Dao<Book> {
    @NonNull private DataSource dataSource;
    @NonNull Dao<Author> authorDao;


    @Override
    public void add(Book entity) {
        String query;
        query = String.format("INSERT INTO book (title, author_id) VALUES ('%s', '%s');",
                entity.getTitle(), entity.getAuthor().getId());
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
    public void update(Book entity) {
        String query = String.format("UPDATE book SET " +
                        "(title, author_id) = ('%s','%s') WHERE book.id = '%d'",
                entity.getTitle(), entity.getAuthor().getId(), entity.getId());
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
    public Book get(int id) {
        String query = "SELECT * FROM book WHERE book.id = ?;";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Book bookFromResultSet = null;
            while (resultSet.next()) {
                bookFromResultSet = new Book(
                        authorDao.get(resultSet.getInt("author_id")),
                        resultSet.getString("title")
                        );
                bookFromResultSet.setId(resultSet.getInt("id"));
            }
            dataSource.getConnection().close();
            return bookFromResultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        String query = "SELECT * FROM book;";
        List<Book> booksFromQuery = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book bookFromResultSet = new Book(
                        authorDao.get(resultSet.getInt("author_id")),
                        resultSet.getString("title")
                );
                bookFromResultSet.setId(resultSet.getInt("id"));
                booksFromQuery.add(bookFromResultSet);
            }
            dataSource.getConnection().close();
            return booksFromQuery;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
