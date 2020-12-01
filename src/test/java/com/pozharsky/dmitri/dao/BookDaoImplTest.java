package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.dao.impl.BookDaoImpl;
import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Binding;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import com.pozharsky.dmitri.exception.DaoException;
import com.pozharsky.dmitri.reader.BookReader;
import com.pozharsky.dmitri.storage.BookWarehouse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookDaoImplTest {
    static final String FILE = "data\\books.txt";
    static final int PRECISION = 4;
    BookReader bookReader;
    BookDaoImpl bookDAOImpl;
    BookWarehouse bookWarehouse;

    @BeforeMethod
    public void setUp() {
        bookReader = new BookReader();
        List<Book> books = bookReader.readBooks(FILE);
        bookWarehouse = BookWarehouse.getInstance();
        bookWarehouse.setBooks(books);
        bookDAOImpl = BookDaoImpl.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        bookReader = null;
        bookDAOImpl = null;
        bookWarehouse = null;
    }

    @Test(enabled = false)
    public void testFindAll() {
    }

    @Test
    public void testFindEntityById() {
        try {
            Book actual = bookDAOImpl.findById(1L);
            Book expect = new Book(1L, "Thinking in Java", Set.of(new Author("Bruce", "Eckel")),
                    PublishingHouse.PITER, 2019, 1168, new BigDecimal(79.65, new MathContext(PRECISION)), Binding.SOLID);
            assertEquals(actual, expect);
        } catch (DaoException e) {
            fail(e.getMessage());
        }
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