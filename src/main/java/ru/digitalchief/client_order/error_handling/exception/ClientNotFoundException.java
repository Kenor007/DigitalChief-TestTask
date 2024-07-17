package ru.digitalchief.client_order.error_handling.exception;

public class ClientNotFoundException extends ResourceNotFoundException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}