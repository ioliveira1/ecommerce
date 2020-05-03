package com.ioliveira.ecommerce.controllers.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, String path, LocalDateTime timestamp) {
        super(status, message, path, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
