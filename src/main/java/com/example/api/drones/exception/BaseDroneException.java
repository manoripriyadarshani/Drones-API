package com.example.api.drones.exception;

/**
 * @Author manorip
 * @create 1/23/23 1:12 PM
 */
public abstract class BaseDroneException extends RuntimeException {

    private String errorCode;
    private String message;
    private String developerMessage;

    public BaseDroneException(String message, String developerMessage, String errorCode) {
        super(message);
        this.message = message;
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
