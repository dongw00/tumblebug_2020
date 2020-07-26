package com.tumblbug.project.common.handler;

import com.tumblbug.project.common.exceptions.ApiRequestException;
import com.tumblbug.project.common.exceptions.CustomRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<?> handleApiRequestException(final ApiRequestException e) {
        return ResponseEntity.status(e.getStatus()).body(new CustomRequestException(e));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, NoSuchElementException.class})
    public ResponseEntity<?> handleIllegalArgumentException(final RuntimeException e) {
        return ResponseEntity.badRequest().body(new CustomRequestException(e));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(final Exception e) {
        log.error("Internal Server Error", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new CustomRequestException(e));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new CustomRequestException(e));
    }
}
