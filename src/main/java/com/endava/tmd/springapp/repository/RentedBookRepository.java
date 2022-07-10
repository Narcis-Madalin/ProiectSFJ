package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {

    RentedBook getRentedBookByBook(AvailableBook book);
}
