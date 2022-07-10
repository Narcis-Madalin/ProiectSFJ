package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByAuthorAndAndTitle(String author, String title);
    Book findBookByTitle(String title);
}
