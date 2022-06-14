package com.example.restfulservice.exception;

// HTTP Status
// 2XX -> OK
// 4XX -> Client
// 5XX -> Server
//@ResponseStatus(HttpStatus.OK) // exception handler 가 더욱 강한 우선순위를 지닌다.
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
