package com.dummy.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dummy.library.data.entity.Author;
import com.dummy.library.data.entity.Book;
import com.dummy.library.data.repository.AuthorRepository;
import com.dummy.library.data.repository.BookRepository;
import com.dummy.library.mapper.AuthorModelMapper;
import com.dummy.library.mapper.BookModelMapper;
import com.dummy.library.service.Impl.AuthorDetailsServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorDetailsServiceImplTest {

  @Mock
  private AuthorRepository authorRepository;

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private AuthorDetailsServiceImpl authorDetailsService;

  @Test
  void findDetailsById_ShouldReturnAuthorDetails_WhenAuthorExists() {
    // Given
    Long authorId = 1L;

    Author authorEntity = new Author();
    authorEntity.setId(authorId);
    authorEntity.setName("John Doe");

    var authorModel = new com.dummy.library.model.Author(authorId, "John Doe");

    Book bookEntity = new Book();
    bookEntity.setId(1L);
    bookEntity.setTitle("Effective Java");

    var bookModel = new com.dummy.library.model.Book(1L, "Effective Java", null);

    when(authorRepository.findById(authorId)).thenReturn(Optional.of(authorEntity));
    when(bookRepository.findByAuthorId(authorId)).thenReturn(List.of(bookEntity));

    // Mocking static methods
    try (MockedStatic<AuthorModelMapper> authorMapperMock = mockStatic(AuthorModelMapper.class);
        MockedStatic<BookModelMapper> bookMapperMock = mockStatic(BookModelMapper.class)) {

      authorMapperMock.when(() -> AuthorModelMapper.mapToAuthorModel(authorEntity)).thenReturn(authorModel);
      bookMapperMock.when(() -> BookModelMapper.mapToModelBook(bookEntity)).thenReturn(bookModel);

      // When
      var result = authorDetailsService.findDetailsById(authorId);

      // Then
      assertNotNull(result);
      assertEquals(authorModel, result.author());
      assertEquals(1, result.books().size());
      assertEquals(bookModel, result.books().get(0));
    }
  }

  @Test
  void findDetailsById_ShouldReturnNull_WhenAuthorNotFound() {
    // Given
    Long authorId = 2L;
    when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

    // When
    var result = authorDetailsService.findDetailsById(authorId);

    // Then
    assertNotNull(result);
    assertNull(result.author());
    assertEquals(0, result.books().size());
  }
}

