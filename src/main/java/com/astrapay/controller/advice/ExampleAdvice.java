package com.astrapay.controller.advice;

import com.astrapay.dto.RespDto;
import com.astrapay.exception.NoteNotFoundException;
import com.astrapay.util.BuildResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
class ExampleAdvice {
    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(Exception.class)
    public void handleConflict() {
        // Nothing to do
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespDto<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return BuildResponse.badRequest("Validation failed", errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<RespDto<Object>> handleNotFound(RuntimeException exception){
        return BuildResponse.notFound(exception.getMessage(), null);
    }
}