package com.brajesh.catalog.domain.product.exception;

import com.brajesh.catalog.domain.product.model.enumeration.FeedbackSeverity;
import com.brajesh.catalog.domain.product.model.enumeration.FeedbackType;

public interface ExceptionCode {
    String getKey();

    String getLabel();

    int getStatus();

    FeedbackType getType();

    FeedbackSeverity getSeverity();
}
