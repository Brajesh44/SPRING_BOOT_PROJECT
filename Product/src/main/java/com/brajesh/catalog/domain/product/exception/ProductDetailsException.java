package com.brajesh.catalog.domain.product.exception;

import com.brajesh.catalog.domain.product.model.enumeration.FeedbackSeverity;
import com.brajesh.catalog.domain.product.model.enumeration.FeedbackType;
import jakarta.validation.constraints.NotNull;

import static com.brajesh.catalog.domain.product.model.enumeration.FeedbackSeverity.WARN;
import static com.brajesh.catalog.domain.product.model.enumeration.FeedbackType.BUS;
import static com.brajesh.catalog.domain.product.model.enumeration.FeedbackType.SEC;

public enum ProductDetailsException implements ExceptionCode {

    /* =======================
       BUSINESS
       ======================= */
    SB_PL_INVALID_INPUT_DATA(
            400,
            "Invalid input data.",
            BUS,
            WARN
    ),

    INVALID_PRODUCT_ID(400, "Product Id parameter is not valid", BUS,WARN),

    SB_PL_PRODUCT_ALREADY_EXISTS(
            409,
            "Product already exists.",
            BUS,
            WARN
    ),

    /* =======================
       SECURITY
       ======================= */
    SB_PL_UNAUTHORIZED(
            401,
            "Unauthorized access.",
            SEC,
            WARN
    ),

    SB_PL_FORBIDDEN(
            403,
            "Access denied.",
            SEC,
            WARN
    ),

    /* =======================
       TECHNICAL
       ======================= */
    SB_PL_DATABASE_ERROR(
            500,
            "Database error occurred.",
            FeedbackType.TEC,
            FeedbackSeverity.ERROR
    ),

    SB_PL_SERVICE_UNAVAILABLE(
            503,
            "Service temporarily unavailable.",
            FeedbackType.TEC,
            FeedbackSeverity.ERROR
    );

    private final int status;
    private final String label;
    private final FeedbackType type;
    private final FeedbackSeverity severity;

    ProductDetailsException(
            int status,
            String label,
            FeedbackType type,
            FeedbackSeverity severity
    ) {
        this.status = status;
        this.label = label;
        this.type = type;
        this.severity = severity;
    }

    @NotNull
    @Override
    public String getKey() {
        return name();
    }

    @NotNull
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @NotNull
    @Override
    public FeedbackType getType() {
        return type;
    }

    @NotNull
    @Override
    public FeedbackSeverity getSeverity() {
        return severity;
    }
}
