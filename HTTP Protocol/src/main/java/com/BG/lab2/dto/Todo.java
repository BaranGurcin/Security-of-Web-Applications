package com.vizja.lab2.dto;

public record Todo(
        Integer id,
        String text,
        boolean completed
) {
}
