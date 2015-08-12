package com.dassault_systemes.diy.web;

import com.dassault_systemes.diy.web.exceptions.EntityAlreadyExistException;
import com.dassault_systemes.diy.web.exceptions.EntityNotFoundException;

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
    public
    @ResponseBody
    ErrorResponse handleConflict(Exception e) {
        logger.debug("conflict with %s", e.getMessage());
        return new ErrorResponse(CONFLICT, "Entity object already exists", e.getClass());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public
    @ResponseBody
    ErrorResponse handleNotFound(Exception e) {
        logger.debug("not found with %s", e.getMessage());
        return new ErrorResponse(NOT_FOUND, "Entity object not found", e.getClass());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, EmptyQueryException.class})
    public
    @ResponseBody
    ErrorResponse handleBadRequest(Exception e) {
        return new ErrorResponse(BAD_REQUEST, e.getMessage(), e.getClass());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public
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

}
