package com.BG.sw.lab6.model;

import java.time.Instant;

public record Token(
        String value,
        Instant expiryTimestamp
) {
}
