package com.example.demo.starter.util.response;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> {
    private T data;
    private List<String> errors;
    private int statusCode;
    private boolean isSuccessful;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public static <T> ServiceResponse<T> success(T data, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        return response;
    }

    public static ServiceResponse<NoContent> success(int statusCode) {
        ServiceResponse<NoContent> response = new ServiceResponse<>();
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        return response;
    }

    public static <T> ServiceResponse<T> failure(List<String> errors, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(errors);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }

    public static <T> ServiceResponse<T> failure(String error, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        List<String> errorList = new ArrayList<>();
        errorList.add(error);
        response.setErrors(errorList);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }

}
