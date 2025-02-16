package com.dummy.library.mapper;

import com.dummy.library.data.entity.Book;
import com.dummy.library.model.BookDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookEntityMapper {

  public static Book buildEntityBook(BookDetails bookDetails) {
    return Book.builder()
        .id(bookDetails.book().id())
        .title(bookDetails.book().title())
        .published(bookDetails.book().publishedAt())
        .authors(AuthorEntityMapper.mapToEntityAuthors(bookDetails.authors()))
        .build();
  }



}
