package com.dummy.library.service.Impl;

import static com.dummy.library.mapper.BookEntityMapper.buildEntityBook;

import com.dummy.library.data.repository.BookRepository;
import com.dummy.library.mapper.BookEntityMapper;
import com.dummy.library.mapper.BookModelMapper;
import com.dummy.library.model.BookDetails;
import com.dummy.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public Long create(@NonNull BookDetails bookDetails) {
    var saved = this.bookRepository.save(buildEntityBook(bookDetails));
    return saved.getId();
  }

  @Override
  public void delete(Long id) {
    this.bookRepository.deleteById(id);
  }

  @Override
  public void update(BookDetails book) {
    this.bookRepository.save(BookEntityMapper.buildEntityBook(book));
  }

  @Override
  public BookDetails findById(Long id) {
    return this.bookRepository.findById(id)
        .map(BookModelMapper::mapToBookDetailsModel)
        .orElse(null);
  }

  @Override
  public Page<BookDetails> fetchAll(Pageable pageable) {
    return this.bookRepository.findAll(pageable)
        .map(BookModelMapper::mapToBookDetailsModel);
  }

}
