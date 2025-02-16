package com.dummy.library.dto;

import org.springframework.lang.NonNull;

public record AuthorCreatedEvent(@NonNull Long authorId) { }
