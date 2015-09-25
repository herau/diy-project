package com.ds.ce.diy.web;

import com.ds.ce.diy.web.exceptions.EntityAlreadyExistException;
import com.ds.ce.diy.web.exceptions.EntityNotFoundException;
import com.ds.ce.diy.web.exceptions.TokenHasExpiredException;
import com.ds.ce.diy.web.exceptions.UserAlreadyRegisteredException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.hibernate.search.exception.EmptyQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;

/**
 * Handle exceptions across the whole application
 */
@ControllerAdvice
public final class ExceptionsHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({EntityAlreadyExistException.class})
    @ResponseBody
    ErrorResponse handleConflict(Exception e) {
        logger.debug("conflict with %s", e.getMessage());
        return new ErrorResponse(CONFLICT, "Entity object already exists", e.getClass());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, javax.persistence.EntityNotFoundException.class})
    @ResponseBody
    ErrorResponse handleNotFound(Exception e) {
        logger.debug("not found with %s", e.getMessage());
        return new ErrorResponse(NOT_FOUND, "Entity object not found", e.getClass());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, EmptyQueryException.class})
    @ResponseBody
    ErrorResponse handleBadRequest(Exception e) {
        return new ErrorResponse(BAD_REQUEST, e.getMessage(), e.getClass());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    ErrorResponse handleUnrecognizedFields(Exception e) {
        String message;

        if (e.getCause() instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException exception = (UnrecognizedPropertyException) e.getCause();

            Collection<Object> knownPropertyIds = exception.getKnownPropertyIds();
            message = String.format("Unrecognized field \"%s\" (%s known properties: %s)", exception.getPropertyName(),
                                    knownPropertyIds.size(), knownPropertyIds);
        } else {
            message = e.getMessage();
        }

        return new ErrorResponse(BAD_REQUEST, message, e.getClass());
    }

    @ResponseStatus(GONE)
    @ExceptionHandler({TokenHasExpiredException.class})
    @ResponseBody
    ErrorResponse handleTokenExpired(Exception e) {
        return new ErrorResponse(GONE, "Token has expired", e.getClass());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({UserAlreadyRegisteredException.class})
    @ResponseBody
    ErrorResponse handleUserAlreadyRegistered(Exception e) {
        return new ErrorResponse(CONFLICT, "User already registered", e.getClass());
    }
}
