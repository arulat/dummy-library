package com.dummy.library.mapper;

import com.dummy.library.data.entity.Author;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorEntityMapper {

  public static Author mapToEntityAuthor(com.dummy.library.model.Author author) {
    return Author.builder()
        .id(author.id())
        .name(author.name())
        .build();
  }

  public static Set<Author> mapToEntityAuthors(List<com.dummy.library.model.Author> authors) {
    return authors.stream()
        .map(author -> Author.builder().id(author.id()).build())
        .collect(Collectors.toSet());
  }


}
