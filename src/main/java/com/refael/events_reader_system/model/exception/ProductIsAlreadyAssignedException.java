package com.refael.events_reader_system.model.exception;

import java.text.MessageFormat;

public class ProductIsAlreadyAssignedException extends RuntimeException {
    public ProductIsAlreadyAssignedException(final Long itemId, final String cartId) {
        super(MessageFormat.format("Product: {0} is already assigned to Event: {1}", itemId, cartId));
    }
}
