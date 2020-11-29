package com.pozharsky.dmitri.storage;

import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.BookWarehouseException;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {
    private static BookWarehouse warehouse;
    private List<Book> books;

    private BookWarehouse() {
        this.books = new ArrayList<>();
    }

    public synchronized static BookWarehouse getInstance() {
        if (warehouse == null) {
            warehouse = new BookWarehouse();
        }
        return warehouse;
    }

    public boolean addBook(Book book) {
        return books.add(book);
    }

    public Book getBook(int index) throws BookWarehouseException {
        if (index < 0 || index >= books.size()) {
            throw new BookWarehouseException("Incorrect index. It should be more than zero and less than size");
        }
        return books.get(index);
    }

    public Book setBook(int index, Book book) throws BookWarehouseException {
        if (index < 0 || index >= books.size()) {
            throw new BookWarehouseException("Incorrect index. It should be more than zero and less than size");
        }
        return books.set(index, book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public int size() {
        return books.size();
    }

    public boolean contains(Book book) {
        return books.contains(book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void setBooks(List<Book> books) {
        this.books = new ArrayList<>(books);
    }
}
