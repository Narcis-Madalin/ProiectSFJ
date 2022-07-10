package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.RentedBook;
import com.endava.tmd.springapp.repository.AvailableBookRepository;
import com.endava.tmd.springapp.repository.BookRepository;
import com.endava.tmd.springapp.repository.RentedBookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentedBookService {

    @Autowired
    private RentedBookRepository rentedBookRepository;

    @Autowired
    private AvailableBookRepository availableBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

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

    public void addManualRentedBook(Long available_book_id, Long userId, LocalDateTime rentedUntil, String rentedPeriod){
        RentedBook rentedBook = new RentedBook();
        rentedBook.setBook(availableBookRepository.findById(available_book_id).get());
        rentedBook.setUser(userRepository.findById(userId).get());
        rentedBook.setRentedUntil(rentedUntil);
        rentedBook.setRentedPeriod(rentedPeriod);
        rentedBookRepository.saveAndFlush(rentedBook);
    }

    public void addRentedBook(String username, String bookTitle, String period){
        if(period.equals("1 week") || period.equals("2 weeks") || period.equals("3 weeks") || period.equals("1 month")) {
            RentedBook rentedBook = new RentedBook();
            rentedBook.setBook(availableBookRepository.getAvailableBookByBook(bookRepository.findBookByTitle(bookTitle)));
            rentedBook.setUser(userRepository.findUserByUsername(username));
            switch (period) {
                case "1 week" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(7));
                case "2 weeks" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(14));
                case "3 weeks" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(21));
                case "1 month" -> rentedBook.setRentedUntil(LocalDateTime.now().plusMonths(1));
            }
            rentedBook.setRentedPeriod(period);
            rentedBookRepository.saveAndFlush(rentedBook);
        }

    }

}
