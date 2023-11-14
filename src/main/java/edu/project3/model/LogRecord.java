package edu.project3.model;

import java.time.LocalDate;

public record LogRecord(String address,
                        String user,
                        LocalDate date,
                        String request,
                        byte[] bodyBytes,
                        String httpReferer,
                        String httpUserAgent) {
    LogRecord(String log) {
        this(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
    }
}

