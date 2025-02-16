package com.dummy.library.dto;

import com.dummy.library.model.Author;
import com.dummy.library.model.Book;
import com.dummy.library.model.BookDetails;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDto {

  @NotBlank
  @Parameter(
      description = "Book title",
      required = true)
  private String title;

  private LocalDate published;

  @NotBlank
  @Parameter(
      description = "Authors ids",
      required = true)
  private List<Long> authors;

  public BookDetails toModel() {
    return new BookDetails(new Book(null, title, published), this.mapToEntityAuthors());
  }

  private List<Author> mapToEntityAuthors() {
    return this.authors.stream().map(id -> new Author(id, null)).toList();
  }
}
