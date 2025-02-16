package com.dummy.library.service;

import com.dummy.library.model.Book;
import com.dummy.library.model.BookDetails;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

  Long create(BookDetails bookDetails);

  void delete(Long id);

  void update(BookDetails book);

  BookDetails findById(Long id);

  Page<BookDetails> fetchAll(Pageable pageable);

}
