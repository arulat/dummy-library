package com.dummy.library.data.repository;

import com.dummy.library.data.entity.Book;
import java.nio.channels.FileChannel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
  List<Book> findByAuthorId(Long authorId);
}
