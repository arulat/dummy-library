package com.dummy.library.service.Impl;

import com.dummy.library.data.repository.AuthorRepository;
import com.dummy.library.exception.KafkaSendException;
import com.dummy.library.mapper.AuthorEntityMapper;
import com.dummy.library.mapper.AuthorModelMapper;
import com.dummy.library.model.Author;
import com.dummy.library.service.AuthorCreatedEventService;
import com.dummy.library.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  private final AuthorCreatedEventService authorCreatedEventService;

  @Override
  public Long create(@NonNull Author author) {
    var saved = this.authorRepository.save(AuthorEntityMapper.mapToEntityAuthor(author));
    this.fireAuthorCreatedEvent(saved);
    return saved.getId();
  }

  private void fireAuthorCreatedEvent(com.dummy.library.data.entity.Author saved) {
    try {
      authorCreatedEventService.fire(saved.getId());
    } catch (JsonProcessingException e) {
      log.error("Failed to send author created event for authorId: {}", saved.getId(), e);
      throw new KafkaSendException(e.getMessage());
    }
  }

  @Override
  public void delete(@NonNull Long id) {
    this.authorRepository.deleteById(id);
  }

  @Override
  public void update(@NonNull Author author) {
    this.authorRepository.save(AuthorEntityMapper.mapToEntityAuthor(author));
  }

  @Override
  public Author findById(@NonNull Long id) {
    return this.authorRepository.findById(id)
        .map(AuthorModelMapper::mapToAuthorModel)
        .orElse(null);
  }

  @Override
  public Page<Author> findAll(@NonNull Pageable pageable) {
    return this.authorRepository.findAll(pageable)
        .map(AuthorModelMapper::mapToAuthorModel);
  }

}
