package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {

}
