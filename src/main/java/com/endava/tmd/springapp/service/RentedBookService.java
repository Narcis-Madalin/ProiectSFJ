package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.RentedBook;
import com.endava.tmd.springapp.repository.AvailableBookRepository;
import com.endava.tmd.springapp.repository.RentedBookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentedBookService {

    private RentedBookRepository rentedBookRepository;

    private AvailableBookRepository availableBookRepository;

    private UserRepository userRepository;

    public RentedBookService(RentedBookRepository rentedBookRepository){
        this.rentedBookRepository = rentedBookRepository;
    }

    public List<RentedBook> getAll(){
        return rentedBookRepository.findAll();
    }

    public RentedBook getById(Long id){
        return rentedBookRepository.findById(id).get();
    }

    public void deleteRentedBook(Long id){
        rentedBookRepository.deleteById(id);
    }

    public void addRentedBook(Long available_book_id, Long userId, LocalDateTime rentedUntil, String rentedPeriod){
        RentedBook rentedBook = new RentedBook();
        rentedBook.setBook(availableBookRepository.findById(available_book_id).get());
        rentedBook.setUser(userRepository.findById(userId).get());
        rentedBook.setRentedUntil(rentedUntil);
        rentedBook.setRentedPeriod(rentedPeriod);
        rentedBookRepository.saveAndFlush(rentedBook);
    }

}
