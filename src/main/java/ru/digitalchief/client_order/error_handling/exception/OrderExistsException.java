package ru.digitalchief.client_order.error_handling.exception;

public class OrderExistsException extends ResourceExistsException {
    public OrderExistsException(String message) {
        super(message);
    }
}