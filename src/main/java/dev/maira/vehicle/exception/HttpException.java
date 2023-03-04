package dev.maira.vehicle.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 1020089924452008056L;
    private final HttpError httpError;
    private final ErrorsEnum errorEnum;

    public HttpException(ErrorsEnum errorEnum, HttpStatus status, String details) {
        super(details);
        this.httpError = HttpError.create(errorEnum, status, details);
        this.errorEnum = errorEnum;
    }
}
