package com.dummy.library.mapper;

import com.dummy.library.model.Book;
import com.dummy.library.model.BookDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookModelMapper {

  public static BookDetails mapToBookDetailsModel(com.dummy.library.data.entity.Book book) {
    return new BookDetails(mapToModelBook(book), AuthorModelMapper.mapToModelAuthors(book.getAuthors()));
  }

  public static Book mapToModelBook(com.dummy.library.data.entity.Book book) {
    return new Book(book.getId(), book.getTitle(), book.getPublished());
  }

}
