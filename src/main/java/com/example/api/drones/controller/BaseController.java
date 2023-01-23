package com.example.api.drones.controller;

import com.example.api.drones.dto.ErrorResponseDTO;
import com.example.api.drones.exception.BaseDroneException;
import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

/**
 * @Author manorip
 * @create 1/23/23 12:40 PM
 */
@ControllerAdvice
public abstract class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(BaseDroneException.class)
    public ResponseEntity<ErrorResponseDTO> handleDroneException(BaseDroneException e) {
        logger.error("Handling BaseDroneException ", e);
        ErrorResponseDTO errorResponse = createErrorResponse(e.getErrorCode(), e.getMessage(), e.getDeveloperMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("Handling EntityNotFoundException ", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-1", "Entity not found", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Handling MethodArgumentNotValidException ", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-2", "Validation Failed", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error("Handling MethodArgumentTypeMismatchException ", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-3", "Validation Failed", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleJsonParseException(JsonParseException e) {
        logger.error("Handling JsonParseException", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-4", "Invalid JSON", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("Handling HttpRequestMethodNotSupportedException", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-5", "Invalid HTTP method", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalServerError(Exception e) {
        logger.error("Handling InternalServerError", e);
        ErrorResponseDTO errorResponse = createErrorResponse("ERR-0", "Internal server error occurred", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDTO createErrorResponse(String errorCode, String message, String developerMessage) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setCode(errorCode);
        errorResponse.setMessage(message);
        errorResponse.setDeveloperMessage(developerMessage);
        return errorResponse;
    }

}
