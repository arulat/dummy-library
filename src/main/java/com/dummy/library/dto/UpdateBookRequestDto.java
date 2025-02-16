package com.dummy.library.dto;

import com.dummy.library.model.Author;
import com.dummy.library.model.Book;
import com.dummy.library.model.BookDetails;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequestDto {
  private String title;
  private LocalDate publishedAt;
  private List<Long> authors;

  public BookDetails toModel(Long id) {
    var book = new Book(id, title, publishedAt);
    return new BookDetails(book, mapToModelAuthors());
  }

  private List<Author> mapToModelAuthors() {
    return authors
        .stream()
        .map(id -> new Author(id, null))
        .toList();
  }
}
