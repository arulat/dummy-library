package com.dummy.library.api;


import com.dummy.library.dto.CreateBookRequestDto;
import com.dummy.library.dto.CreateBookResponseDto;
import com.dummy.library.dto.UpdateBookRequestDto;
import com.dummy.library.model.BookDetails;
import com.dummy.library.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
@Tag(name = "Book", description = "Book API")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public CreateBookResponseDto createBook(@RequestBody CreateBookRequestDto body) {
    var id = bookService.create(body.toModel());
    return new CreateBookResponseDto(id);
  }

  @GetMapping("/{id}")
  public BookDetails getBookById(@PathVariable Long id) {
    return bookService.findById(id);
  }

  @GetMapping
  public Page<BookDetails> getAllBooks(Pageable pageable) {
    return bookService.fetchAll(pageable);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequestDto body) {
    var bookDetails = body.toModel(id);
    bookService.update(bookDetails);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
