package com.brajesh.catalog.domain.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
@AllArgsConstructor
public class ApiErrorResponse {
    private final String code;
    private final String message;
    private final String type;
    private final int status;
    private final Instant timestamp;
    public static ApiErrorResponse from(ExceptionCode exceptionCode) {
        return new ApiErrorResponse(
                exceptionCode.getKey(),
                exceptionCode.getLabel(),
                exceptionCode.getType().name(),
                exceptionCode.getStatus(),
                Instant.now()
        );
    }
}
