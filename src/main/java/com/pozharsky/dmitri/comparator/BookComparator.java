package com.pozharsky.dmitri.comparator;

import com.pozharsky.dmitri.entity.Binding;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;

import java.math.BigDecimal;
import java.util.Comparator;

public enum BookComparator implements Comparator<Book> {
    ID {
        @Override
        public int compare(Book o1, Book o2) {
            long id1 = o1.getId();
            long id2 = o2.getId();
            return Long.compare(id1, id2);
        }
    },
    NAME {
        @Override
        public int compare(Book o1, Book o2) {
            String name1 = o1.getName();
            String name2 = o2.getName();
            return name1.compareTo(name2);
        }
    },
    PUBLISHING_HOUSE {
        @Override
        public int compare(Book o1, Book o2) {
            PublishingHouse publishingHouse1 = o1.getPublishingHouse();
            PublishingHouse publishingHouse2 = o2.getPublishingHouse();
            return publishingHouse1.compareTo(publishingHouse2);
        }
    },
    YEAR {
        @Override
        public int compare(Book o1, Book o2) {
            int year1 = o1.getYear();
            int year2 = o2.getYear();
            return Integer.compare(year1, year2);
        }
    },
    AMOUNT_PAGE {
        @Override
        public int compare(Book o1, Book o2) {
            int amountPage1 = o1.getAmountPage();
            int amountPage2 = o2.getAmountPage();
            return Integer.compare(amountPage1, amountPage2);
        }
    },
    PRICE {
        @Override
        public int compare(Book o1, Book o2) {
            BigDecimal price1 = o1.getPrice();
            BigDecimal price2 = o2.getPrice();
            return price1.compareTo(price2);
        }
    },
    BINDING {
        @Override
        public int compare(Book o1, Book o2) {
            Binding binding1 = o1.getBinding();
            Binding binding2 = o2.getBinding();
            return binding1.compareTo(binding2);
        }
    }
}
