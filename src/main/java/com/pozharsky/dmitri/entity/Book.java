package com.pozharsky.dmitri.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Book {
    private long id;
    private String name;
    private Set<Author> authors;
    private PublishingHouse publishingHouse;
    private int year;
    private int amountPage;
    private BigDecimal price;
    private Binding binding;

    public Book() {
    }

    public Book(long id, String name, Set<Author> authors, PublishingHouse publishingHouse, int year, int amountPage, BigDecimal price, Binding binding) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.amountPage = amountPage;
        this.price = price;
        this.binding = binding;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return new HashSet<>(authors);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = new HashSet<>(authors);
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmountPage() {
        return amountPage;
    }

    public void setAmountPage(int amountPage) {
        this.amountPage = amountPage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (year != book.year) return false;
        if (amountPage != book.amountPage) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        if (publishingHouse != book.publishingHouse) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        return binding == book.binding;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + amountPage;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (binding != null ? binding.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", publishingHouse=").append(publishingHouse);
        sb.append(", year=").append(year);
        sb.append(", amountPage=").append(amountPage);
        sb.append(", price=").append(price);
        sb.append(", binding=").append(binding);
        sb.append('}');
        return sb.toString();
    }
}
