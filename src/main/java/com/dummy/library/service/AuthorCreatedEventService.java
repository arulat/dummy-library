package com.dummy.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthorCreatedEventService {
  void fire(Long authorId) throws JsonProcessingException;
}
