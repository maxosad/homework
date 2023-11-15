package edu.project3.model;

import java.time.LocalDateTime;

public record LogRecord(String address,
                        String user,
                        LocalDateTime date,
                        String request,
                        String resource,
                        String status,
                        String bodyBytes,
                        String httpReferer,
                        String httpUserAgent) {
//    LogRecord(String log) {
//        this(
//            null,
//            null,
//            null,
//            null,
//            null,
//            null,
//            null,
//            null
//        );
//    }
}

