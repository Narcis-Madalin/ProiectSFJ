package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByAuthorAndAndTitle(String author, String title);
    Book findBookByTitle(String title);

    List<Book> getBookByAuthorOrTitle(Optional<String> author, Optional<String> title);
}
