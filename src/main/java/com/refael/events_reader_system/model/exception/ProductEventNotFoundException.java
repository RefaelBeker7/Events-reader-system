package com.refael.events_reader_system.model.exception;

import java.text.MessageFormat;

public class ProductEventNotFoundException extends RuntimeException {

    public ProductEventNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find Event with id: {0}", id));
    }

}
