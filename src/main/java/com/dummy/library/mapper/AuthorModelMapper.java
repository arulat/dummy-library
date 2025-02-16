package com.dummy.library.mapper;

import com.dummy.library.model.Author;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorModelMapper {

  public static Author mapToAuthorModel(com.dummy.library.data.entity.Author entityAuthor) {
    return new Author(entityAuthor.getId(), entityAuthor.getName());
  }

  public static List<Author> mapToModelAuthors(
      Set<com.dummy.library.data.entity.Author> entityAuthors) {

    return entityAuthors.stream()
        .map(author -> new Author(author.getId(), author.getName()))
        .collect(Collectors.toList());
  }

}
