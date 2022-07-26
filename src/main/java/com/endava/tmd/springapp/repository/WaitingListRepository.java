package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    List<WaitingList> getWaitingListByAvailableBook(AvailableBook availableBook);
}
