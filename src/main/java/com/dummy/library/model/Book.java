package com.dummy.library.model;

import java.time.LocalDate;

public record Book(
    Long id,
    String title,
    LocalDate publishedAt
) {}
