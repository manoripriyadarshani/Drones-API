package com.example.api.drones.dto;

/**
 * @Author manorip
 * @create 1/23/23 12:48 PM
 */
public class ErrorResponseDTO {

    private String code;
    private String message;
    private String developerMessage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
}
