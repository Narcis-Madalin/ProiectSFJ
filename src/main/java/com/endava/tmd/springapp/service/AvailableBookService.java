package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.Book;
import com.endava.tmd.springapp.entity.User;
import com.endava.tmd.springapp.repository.AvailableBookRepository;
import com.endava.tmd.springapp.repository.BookRepository;
import com.endava.tmd.springapp.repository.RentedBookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableBookService {

    @Autowired
    private AvailableBookRepository availableBookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentedBookRepository rentedBookRepository;

    public AvailableBookService(AvailableBookRepository availableBookRepository){
        this.availableBookRepository = availableBookRepository;
    }

    public List<AvailableBook> getAll(){
        return availableBookRepository.findAll();
    }

    public AvailableBook getById(Long id){
        return availableBookRepository.findById(id).get();
    }

    public void addManualAvailableBook(Long bookId, Long ownerId){
        AvailableBook availableBook = new AvailableBook();
        availableBook.setBook(bookRepository.findById(bookId).get());
        availableBook.setOwner(userRepository.findById(ownerId).get());
        availableBookRepository.saveAndFlush(availableBook);
    }

    public Object addAvailableBook(String bookTitle, String username){

        Book currentBook = bookRepository.findBookByTitle(bookTitle);
        User currentUser = userRepository.findUserByUsername(username);

        List<AvailableBook> currentBooks =  availableBookRepository.getAvailableBooksByBook(currentBook);

        for(AvailableBook book : currentBooks){
            if(book.getOwner() == currentUser){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if(currentBook != null && currentUser != null) {
            AvailableBook availableBook = new AvailableBook();
            availableBook.setBook(currentBook);
            availableBook.setOwner(currentUser);
            availableBookRepository.saveAndFlush(availableBook);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteAvailableBook(Long availableBookId){
        availableBookRepository.deleteById(availableBookId);
    }

    public List<AvailableBook> getAllAvailableBooksForCurrentUser(String username){

        User currentUser = userRepository.findUserByUsername(username);

        List<AvailableBook> booksList = availableBookRepository.getAllAvailableBooksForCurrentUser(currentUser.getUserId());
        List<AvailableBook> availableBooks = new ArrayList<>();

        for(AvailableBook book : booksList){
            if(rentedBookRepository.getRentedBookByBook(book) == null){
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }

}
