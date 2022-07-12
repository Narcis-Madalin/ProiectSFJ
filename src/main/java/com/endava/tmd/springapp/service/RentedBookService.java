package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.RentedBook;
import com.endava.tmd.springapp.entity.User;
import com.endava.tmd.springapp.repository.AvailableBookRepository;
import com.endava.tmd.springapp.repository.BookRepository;
import com.endava.tmd.springapp.repository.RentedBookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

    public Object addRentedBook(String username, String bookTitle, String period, String owner){

        List<AvailableBook> currentBooks = availableBookRepository.getAvailableBooksByBook(bookRepository.findBookByTitle(bookTitle));
        User currentUser = userRepository.findUserByUsername(username);
        AvailableBook currentBook = null;

        for (AvailableBook book : currentBooks){
            if(book.getOwner() == userRepository.findUserByUsername(owner)){
                currentBook = book;
            }
        }

        if((period.equals("1 week") || period.equals("2 weeks") || period.equals("3 weeks") || period.equals("1 month"))
                && currentBook != null
                && rentedBookRepository.getRentedBookByBook(currentBook) == null
                && currentUser != null
                && currentUser != currentBook.getOwner()) {

            RentedBook rentedBook = new RentedBook();
            rentedBook.setBook(currentBook);
            rentedBook.setUser(currentUser);
            switch (period) {
                case "1 week" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(7));
                case "2 weeks" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(14));
                case "3 weeks" -> rentedBook.setRentedUntil(LocalDateTime.now().plusDays(21));
                case "1 month" -> rentedBook.setRentedUntil(LocalDateTime.now().plusMonths(1));
            }
            rentedBook.setRentedPeriod(period);
            rentedBookRepository.saveAndFlush(rentedBook);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public Object seeBorrowerAndRentedUntil(String username){
        HashMap<String, LocalDateTime> borrowerAndRentedUntil = new HashMap<>(); // pastreaza userul care a imprumutat cartea si cand trebuie sa o returneze

        //HashMap<String, HashMap<String, LocalDateTime>> booksAndBorrower = new HashMap<>(); // pastreaza numele cartii imprumutate + userul si cand trebuie sa o returneze

        User owner = userRepository.findUserByUsername(username);

        if(owner != null) {
            List<AvailableBook> booksOfTheOwner = availableBookRepository.getAvailableBooksByOwner(owner);

            List<RentedBook> rentedBooksOfTheOwner = new ArrayList<>();

            List<RentedBook> rentedBookList;

            for (AvailableBook book : booksOfTheOwner) {
                if (rentedBookRepository.getRentedBooksByBook(book) != null) {
                    //rentedBooksOfTheOwner.add(rentedBookRepository.getRentedBookByBook(book));
                    rentedBookList = rentedBookRepository.getRentedBooksByBook(book);
                    for (RentedBook rentedBook : rentedBookList) {
                        if (rentedBook.getBook().getOwner() == owner) {
                            rentedBooksOfTheOwner.add(rentedBook);
                        }
                    }
                }
            }

            for (RentedBook rentedBook : rentedBooksOfTheOwner) {
                borrowerAndRentedUntil.put(rentedBook.getUser().getUsername(), rentedBook.getRentedUntil());
            }


            return borrowerAndRentedUntil;
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
