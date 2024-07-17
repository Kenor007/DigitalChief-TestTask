package ru.digitalchief.client_order.error_handling.exception;

public class ResourceExistsException extends BadRequestException {
    public ResourceExistsException(String message) {
        super(message);
    }
}