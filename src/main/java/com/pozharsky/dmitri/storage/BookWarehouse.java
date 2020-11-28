package com.pozharsky.dmitri.storage;

import com.pozharsky.dmitri.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {
    private static BookWarehouse warehouse;
    private final List<Book> list = new ArrayList<>();

    private BookWarehouse() {
    }

    public synchronized static BookWarehouse getInstance() {
        if (warehouse == null) {
            warehouse = new BookWarehouse();
        }
        return warehouse;
    }

    public boolean addBook(Book book) {
        return list.add(book);
    }

    public Book getBook(int index) {
        return list.get(index);
    }

    public Book setBook(int index, Book book) {
        return list.set(index, book);
    }

    public boolean removeBook(Book book) {
        return list.remove(book);
    }

    public int size() {
        return list.size();
    }

    public boolean contains(Book book) {
        return list.contains(book);
    }
}
