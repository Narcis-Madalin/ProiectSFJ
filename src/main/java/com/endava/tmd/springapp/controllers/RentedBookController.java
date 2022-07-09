package com.endava.tmd.springapp.controllers;

import com.endava.tmd.springapp.entity.RentedBook;
import com.endava.tmd.springapp.service.RentedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rented_books")
public class RentedBookController {

    @Autowired
    private RentedBookService rentedBookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RentedBook> getAllRentedBooks(){
        return rentedBookService.getAll();
    }

    @RequestMapping(params = "rented_book_id", method = RequestMethod.GET)
    public Object getRentedBook(@RequestParam Long rentedBookId){
        if(rentedBookService.getById(rentedBookId) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return rentedBookService.getById(rentedBookId);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRentedBook(@RequestParam Long availableBookId, @RequestParam Long userId, @RequestParam LocalDateTime rentedUntil, @RequestParam String rentedPeriod){
        rentedBookService.addRentedBook(availableBookId, userId, rentedUntil, rentedPeriod);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteRentedBook(@RequestParam Long rentedBookId){
        rentedBookService.deleteRentedBook(rentedBookId);
    }
}