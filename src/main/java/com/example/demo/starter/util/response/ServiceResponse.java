package com.example.demo.starter.util.response;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ServiceResponse<T> {
    private T data;
    private List<String> errors;
    private int statusCode;
    private boolean isSuccessful;
    private String traceId;

    // Success response with data
    public static <T> ServiceResponse<T> success(T data, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        response.setTraceId(MDC.get("traceId"));
        return response;
    }

    // Success response without data
    public static ServiceResponse<NoContent> success(int statusCode) {
        ServiceResponse<NoContent> response = new ServiceResponse<>();
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        response.setTraceId(MDC.get("traceId"));
        return response;
    }

    public static <T> ServiceResponse<T> failure(List<String> errors, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(errors);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        response.setTraceId(MDC.get("traceId"));  // ✅ TraceId otomatik ekleniyor
        return response;
    }

    public static <T> ServiceResponse<T> failure(String error, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        List<String> errorList = new ArrayList<>();
        errorList.add(error);
        response.setErrors(errorList);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        response.setTraceId(MDC.get("traceId"));  // ✅ TraceId otomatik ekleniyor
        return response;
    }
}
