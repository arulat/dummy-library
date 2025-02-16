package com.dummy.library.service;


import com.dummy.library.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface AuthorService {
  Long create(@NonNull Author author);

  void delete(@NonNull Long id);

  void update(@NonNull Author author);

  Author findById(@NonNull Long id);

  Page<Author> findAll(@NonNull Pageable pageable);
}
