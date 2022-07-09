package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

}
