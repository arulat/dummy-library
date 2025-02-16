package com.dummy.library.service.Impl;

import com.dummy.library.data.repository.AuthorRepository;
import com.dummy.library.data.repository.BookRepository;
import com.dummy.library.mapper.AuthorEntityMapper;
import com.dummy.library.mapper.AuthorModelMapper;
import com.dummy.library.mapper.BookModelMapper;
import com.dummy.library.model.Author;
import com.dummy.library.model.AuthorDetails;
import com.dummy.library.service.AuthorDetailsService;
import com.dummy.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorDetailsServiceImpl implements AuthorDetailsService {

  private final AuthorRepository authorRepository;

  private final BookRepository bookRepository;

  @Override
  public AuthorDetails findDetailsById(@NonNull Long id) {
    var author = this.authorRepository.findById(id)
        .map(AuthorModelMapper::mapToAuthorModel)
        .orElse(null);

    var books = this.bookRepository.findByAuthorId(id)
        .stream().map(
            BookModelMapper::mapToModelBook
        ).toList();

    return new AuthorDetails(author, books);
  }
}
