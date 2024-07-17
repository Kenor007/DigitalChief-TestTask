package ru.digitalchief.client_order.error_handling.constant;

public final class ExceptionAnswer {
    public static final String POSITIVE_ID = "Id must not be less than one";
    public static final String CLIENT_NOT_FOUND = "The client with id %s not found";
    public static final String CLIENT_ALREADY_EXISTS = "The client already exists";
    public static final String ORDER_NOT_FOUND = "The order with id %s not found";
    public static final String ORDER_ALREADY_EXISTS = "The order already exists";
}