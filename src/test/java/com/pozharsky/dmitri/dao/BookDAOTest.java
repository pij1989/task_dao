package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Binding;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import com.pozharsky.dmitri.reader.BookReader;
import com.pozharsky.dmitri.storage.BookWarehouse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class BookDAOTest {
    static final String FILE = "data\\books.txt";
    BookReader bookReader;
    BookDAO bookDAO;
    BookWarehouse bookWarehouse;

    @BeforeMethod
    public void setUp() {
        bookReader = new BookReader();
        List<Book> books = bookReader.readBooks(FILE);
        bookWarehouse = BookWarehouse.getInstance();
        bookWarehouse.setBooks(books);
        bookDAO = new BookDAO();
    }

    @AfterMethod
    public void tearDown() {
        bookReader = null;
        bookDAO = null;
        bookWarehouse = null;
    }

    @Test(enabled = false)
    public void testFindAll() {
    }

    @Test
    public void testFindEntityById() {
        Book actual = bookDAO.findEntityById(1L);
        Book expect = new Book(1L, "Thinking in Java", Set.of(new Author("Bruce", "Eckel")),
                PublishingHouse.PITER, 2019, 1168, 79.65, Binding.SOLID);
        assertEquals(actual, expect);
    }

    @Test(enabled = false)
    public void testDeleteById() {
    }

    @Test(enabled = false)
    public void testDelete() {
    }

    @Test(enabled = false)
    public void testCreate() {
    }

    @Test(enabled = false)
    public void testUpdate() {
    }
}