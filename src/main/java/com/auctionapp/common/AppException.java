package com.auctionapp.common;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final String INTERNAL_ERROR = "internal-error";
    public static final String UNAUTHORIZED_ERROR = "unauthorized";
    public static final String VALIDATION_ERROR = "validation-error";

    private final String code;

    public AppException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
