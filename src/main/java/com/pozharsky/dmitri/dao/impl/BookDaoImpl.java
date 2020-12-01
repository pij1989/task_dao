package com.pozharsky.dmitri.dao.impl;

import com.pozharsky.dmitri.dao.BaseDao;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.BookWarehouseException;
import com.pozharsky.dmitri.exception.DaoException;
import com.pozharsky.dmitri.storage.BookWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BaseDao<Book> {
    private static final Logger logger = LogManager.getLogger(Book.class);
    private static BookDaoImpl bookDao;

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        if (bookDao == null) {
            bookDao = new BookDaoImpl();
        }
        return bookDao;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book book = bookWarehouse.getBook(i);
                books.add(book);
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
            throw new DaoException("Can not find books:" + e);
        }
        return books;
    }

    @Override
    public Book findById(long id) throws DaoException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        Book book = null;
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book b = bookWarehouse.getBook(i);
                if (b.getId() == id) {
                    book = b;
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
            throw new DaoException("Can not find a book: " + e);
        }
        return book;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book book = bookWarehouse.getBook(i);
                if (book.getId() == id) {
                    return bookWarehouse.removeBook(book);
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
            throw new DaoException("Can not delete a book: " + e);
        }
        throw new DaoException("Can not delete a book. This book doesn't exist");
    }

    @Override
    public boolean delete(Book entity) throws DaoException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        if (!bookWarehouse.contains(entity)) {
            throw new DaoException("Can not delete a book. This book doesn't exist");
        }
        return bookWarehouse.removeBook(entity);
    }

    @Override
    public boolean create(Book entity) throws DaoException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        if (bookWarehouse.contains(entity)) {
            throw new DaoException("Can not add a book. This book exists");
        }
        return bookWarehouse.addBook(entity);
    }

    @Override
    public Book update(Book entity) {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        Book book = null;
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book b = bookWarehouse.getBook(i);
                if (entity.getId() == b.getId()) {
                    bookWarehouse.setBook(i, entity);
                    book = entity;
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
        }
        return book;
    }
}
