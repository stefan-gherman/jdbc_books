package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.Book;
import com.codecool.books.model.Dao;
import com.codecool.books.view.UserInterface;

import java.sql.Date;

public class BookManager extends Manager{

    Dao<Book> genericDao;
    Dao<Author> authorDao;

    public BookManager(UserInterface ui, Dao<Book> genericDao, Dao<Author> authorDao) {
        super(ui);
        this.genericDao = genericDao;
        this.authorDao = authorDao;
    }


    protected String getName() {
        return "Book Manager";
    }

    protected void list() {
        for ( Book entity : genericDao.getAll()) {
            ui.println(entity);
        }
    }

    @Override
    protected  void add() {
        String title = ui.readString("Book Title", "X");
        int authorId = ui.readInt("Author ID", 1);
        genericDao.add(new Book(authorDao.get(authorId), title));
    }

    @Override
    protected void edit() {
        int id = ui.readInt("Book ID", 0);
        Book book = genericDao.get(id);

        if (book == null) {
            ui.println("Author not found!");
            return;
        }
        ui.println(book);

        String title = ui.readString("Title", book.getTitle());
        int authorId = ui.readInt("authorId", book.getAuthor().getId());
        book.setTitle(title);
        book.setAuthor(authorDao.get(authorId));
        genericDao.update(book);
    }
}
