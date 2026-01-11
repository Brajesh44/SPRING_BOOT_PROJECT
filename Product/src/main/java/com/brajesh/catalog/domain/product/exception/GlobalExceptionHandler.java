package com.brajesh.catalog.domain.product.exception;

import com.brajesh.catalog.domain.product.model.enumeration.FeedbackType;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleProductException(
            ProductException ex
    ) {
        ProductDetailsException code = ex.getCode();
        ApiErrorResponse response = ApiErrorResponse.from(code);

        logBasedOnSeverity(code, ex, response);

        return Mono.just(
                ResponseEntity
                        .status(code.getStatus())
                        .body(response)
        );
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleUnexpectedException(
            Throwable ex
    ) {
        log.error("UNEXPECTED ERROR", ex);

        ApiErrorResponse response = new ApiErrorResponse(
                "INTERNAL_ERROR",
                "Something went wrong. Please try again later.",
                FeedbackType.TEC.name(),
                500,
                Instant.now()
        );

        return Mono.just(ResponseEntity.status(500).body(response));
    }

    private void logBasedOnSeverity(
            ExceptionCode code,
            Throwable ex,
            ApiErrorResponse response
    ) {
        switch (code.getSeverity()) {
            case INFO -> log.info("INFO: {}", response);
            case WARN -> log.warn("WARN: {}", response);
            case ERROR -> log.error("ERROR: {}", response, ex);
        }
    }
}

