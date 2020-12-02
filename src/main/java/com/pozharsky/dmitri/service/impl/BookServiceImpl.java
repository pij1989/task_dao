package com.pozharsky.dmitri.service.impl;

import com.pozharsky.dmitri.dao.impl.BookDaoImpl;
import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import com.pozharsky.dmitri.exception.DaoException;
import com.pozharsky.dmitri.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl bookService;
    private BookDaoImpl bookDao = BookDaoImpl.getInstance();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            books.addAll(bookDao.findAll());
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findAll(Comparator<Book> comparator) {
        List<Book> books = new ArrayList<>();
        try {
            books.addAll(bookDao.findAll());
            books.sort(comparator);
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findBookByAuthor(Author author, Comparator<Book> comparator) {
        List<Book> books = null;
        try {
            books = bookDao.findAll().stream()
                    .filter(as -> {
                        boolean flag = false;
                        Set<Author> authors = as.getAuthors();
                        for (Author a : authors) {
                            if (a.equals(author)) {
                                flag = true;
                            }
                        }
                        return flag;
                    })
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findBookByPublishingHouse(PublishingHouse publishingHouse, Comparator<Book> comparator) {
        return null;
    }

    @Override
    public List<Book> findBookAfterYear(int year, Comparator<Book> comparator) {
        return null;
    }

    @Override
    public Optional<Book> findById(long id) {
        Book book = null;
        try {
            book = bookDao.findById(id);
        } catch (DaoException e) {
            logger.error("Can not find book: " + e);
        }
        return book == null ? Optional.empty() : Optional.of(book);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Book entity) {
        return false;
    }

    @Override
    public boolean create(Book entity) throws DaoException {
        return false;
    }

    @Override
    public Optional<Book> update(Book entity) {
        return Optional.empty();
    }
}
