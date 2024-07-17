package ru.digitalchief.client_order.error_handling.exception;

public class ClientExistsException extends ResourceExistsException {
    public ClientExistsException(String message) {
        super(message);
    }
}