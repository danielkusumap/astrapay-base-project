package com.astrapay.util;

import com.astrapay.dto.RespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BuildResponse {

    private BuildResponse(){}

    public static <T> ResponseEntity<RespDto<T>> success(String message, T data) {
        return build(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<RespDto<T>> created(String message, T data) {
        return build(HttpStatus.CREATED, message, data);
    }

    public static <T> ResponseEntity<RespDto<T>> badRequest(String message, T data) {
        return build(HttpStatus.BAD_REQUEST, message, data);
    }

    public static <T> ResponseEntity<RespDto<T>> notFound(String message, T data) {
        return build(HttpStatus.NOT_FOUND, message, data);
    }

    private static <T> ResponseEntity<RespDto<T>> build(HttpStatus status, String message, T data) {
        RespDto<T> respDto = RespDto.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(respDto, status);
    }
}
