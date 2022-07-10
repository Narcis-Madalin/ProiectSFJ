package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.WaitingList;
import com.endava.tmd.springapp.repository.BookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import com.endava.tmd.springapp.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<WaitingList> getAllWaitingUsers(){
        return waitingListRepository.findAll();
    }

    public WaitingList getWaitingUserById(Long id){
        return waitingListRepository.findById(id).get();
    }

    public void deleteWaitingUser(Long id){
        waitingListRepository.deleteById(id);
    }

    public void addWaitingUser(Long book_id, Long userId, Long waitingNumber){
        WaitingList waitingList = new WaitingList();
        waitingList.setBook(bookRepository.findById(book_id).get());
        waitingList.setWaitingUser(userRepository.findById(userId).get());
        waitingList.setWaitingNumber(waitingNumber);
        waitingListRepository.saveAndFlush(waitingList);
    }
}
