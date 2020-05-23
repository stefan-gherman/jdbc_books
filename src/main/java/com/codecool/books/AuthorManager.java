package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.Dao;
import com.codecool.books.view.UserInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class AuthorManager extends Manager {
    Dao<Author> authorDao;
    Logger logger = LoggerFactory.getLogger(AuthorManager.class);

    public AuthorManager(UserInterface ui, Dao<Author> authorDao) {
        super(ui);
        this.authorDao = authorDao;
        logger.info("Manager Created at {}", new java.util.Date());
        System.out.println("Debug:" + logger.isDebugEnabled());
        System.out.println("Error:" + logger.isErrorEnabled());
        System.out.println("Warn:" + logger.isWarnEnabled());
    }

    @Override
    protected void add() {
        logger.info("Adder started...");
        logger.warn("Warning");
        String firstName = ui.readString("First name", "X");
        String lastName = ui.readString("Last name", "Y");
        Date birthDate = ui.readDate("Birth date", Date.valueOf("1900-01-01"));
        try {
            logger.info("Adding author with {}, {}, {}.", firstName, lastName, birthDate);
            authorDao.add(new Author(firstName, lastName, birthDate));
        } catch (Exception e) {
            logger.warn("Exception raised");
            logger.error("Something went terribly wrong.");
        }
        logger.info("Adder stopped");

    }

    @Override
    protected String getName() {
        logger.trace("Get Name");
        return "Author Manager";
    }

    @Override
    protected void list() {
        logger.info("Listing Authors");
        for (Author author: authorDao.getAll()) {
            ui.println(author);
        }
    }

    @Override
    protected void edit() {
        logger.info("Edit Started");
        int id = ui.readInt("Author ID", 0);
        logger.debug("Editing author with id {}", id);
        Author author = authorDao.get(id);
        if (author == null) {
            logger.warn("Author with id {} was not found!", id);
            ui.println("Author not found!");
            return;
        }
        ui.println(author);

        String firstName = ui.readString("First name", author.getFirstName());
        String lastName = ui.readString("Last name", author.getLastName());
        Date birthDate = ui.readDate("Birth date", author.getBirthDate());
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        try {
            authorDao.update(author);
        } catch (Exception e) {
            logger.error("Something went wrong");
        }
        logger.debug("Editing author with the following values: {}, {}, {}", firstName, lastName, birthDate);
        logger.info("Edit Finished");

    }
}
