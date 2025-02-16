package com.dummy.library.model;

import java.util.List;
import org.springframework.lang.NonNull;

public record BookDetails(
    @NonNull Book book,
    @NonNull List<Author> authors
) {}
