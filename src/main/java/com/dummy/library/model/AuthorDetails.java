package com.dummy.library.model;

import java.util.List;

public record AuthorDetails(
    Author author,
    List<Book> books
) {}
