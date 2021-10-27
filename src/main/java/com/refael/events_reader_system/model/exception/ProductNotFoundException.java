package com.refael.events_reader_system.model.exception;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find Product with id: {0}", id));
    }
}
