package com.BG.sw.lab8.lib.dto;

import java.time.Instant;

public record Apikey (String keyValue, Instant expirationTime, boolean isActive) {
}
