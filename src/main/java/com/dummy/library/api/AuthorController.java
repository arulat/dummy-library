package com.dummy.library.api;

import com.dummy.library.dto.CreateAuthorRequestDto;
import com.dummy.library.dto.CreateAuthorResponseDto;
import com.dummy.library.dto.UpdateAuthorRequestDto;
import com.dummy.library.model.Author;
import com.dummy.library.model.AuthorDetails;
import com.dummy.library.service.AuthorDetailsService;
import com.dummy.library.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/author")
@Tag(name = "Author", description = "Author API")
public class AuthorController {

  private final AuthorService authorService;

  private final AuthorDetailsService authorDetailsService;

  @PostMapping
  public CreateAuthorResponseDto createBook(@RequestBody CreateAuthorRequestDto body) {
    var id = authorService.create(body.toModel());
    return new CreateAuthorResponseDto(id);
  }

  @GetMapping("/{id}")
  public Author getAuthorById(@PathVariable Long id) {
    return authorService.findById(id);
  }

  @GetMapping("/{id}/details")
  public AuthorDetails getAuthorDetailsById(@PathVariable Long id) {
    return this.authorDetailsService.findDetailsById(id);
  }

  @GetMapping
  public Page<Author> getAllAuthors(
      @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return authorService.findAll(pageable);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAuthor(@PathVariable Long id, @RequestBody UpdateAuthorRequestDto body) {
    var author = body.toModel(id);
    authorService.update(author);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
