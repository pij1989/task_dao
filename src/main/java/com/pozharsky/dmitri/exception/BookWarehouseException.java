package com.pozharsky.dmitri.exception;

public class BookWarehouseException extends Exception {
    public BookWarehouseException() {
        super();
    }

    public BookWarehouseException(String message) {
        super(message);
    }

    public BookWarehouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookWarehouseException(Throwable cause) {
        super(cause);
    }
}
