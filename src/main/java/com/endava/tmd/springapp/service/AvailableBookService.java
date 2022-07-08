package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.AvailableBook;
import com.endava.tmd.springapp.entity.User;
import com.endava.tmd.springapp.repository.AvailableBookRepository;
import com.endava.tmd.springapp.repository.BookRepository;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableBookService {

    private AvailableBookRepository availableBookRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public AvailableBookService(AvailableBookRepository availableBookRepository){
        this.availableBookRepository = availableBookRepository;
    }

    public List<AvailableBook> getAll(){
        return availableBookRepository.findAll();
    }

    public AvailableBook getById(Long id){
        return availableBookRepository.findById(id).get();
    }

    public void addAvailableBook(Long bookId, Long ownerId){
        AvailableBook availableBook = new AvailableBook();
        availableBook.setBook(bookRepository.findById(bookId).get());
        availableBook.setOwner(userRepository.findById(ownerId).get());
        availableBookRepository.saveAndFlush(availableBook);
    }

    public void deleteAvailableBook(Long availableBookId){
        availableBookRepository.deleteById(availableBookId);
    }

}
