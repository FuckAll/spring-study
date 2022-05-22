package com.example;


import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TestSample {

    @Test
    public void testUTC() {
        final Instant instant = Instant.now();
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        System.out.println("now = " + localDateTime);
    }
}
