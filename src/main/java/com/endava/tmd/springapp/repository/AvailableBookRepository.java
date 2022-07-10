package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.Book;
import com.endava.tmd.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvailableBookRepository extends JpaRepository<AvailableBook, Long> {

    @Query("select a from AvailableBook a where a.owner.userId <> :userId")
    List<AvailableBook> getAllAvailableBooksForCurrentUser(Long userId);

    AvailableBook getAvailableBookByBook(Book book);

    @Query("select a from AvailableBook a where a.owner.userId = :owner")
    List<AvailableBook> getAvailableBooksByOwner(User owner);
}
