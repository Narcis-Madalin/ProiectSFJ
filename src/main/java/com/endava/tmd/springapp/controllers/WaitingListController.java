package com.endava.tmd.springapp.controllers;

import com.endava.tmd.springapp.entity.WaitingList;
import com.endava.tmd.springapp.service.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("waiting_list")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> getAllWaitingUsers(){
        return waitingListService.getAllWaitingUsers();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Object getWaitingUserById(@RequestParam Long id){
        if(waitingListService.getWaitingUserById(id) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return waitingListService.getWaitingUserById(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addWaitingUser(@RequestParam Long bookId, @RequestParam Long userId, @RequestParam Long waitingNumber){
        waitingListService.addWaitingUser(bookId, userId, waitingNumber);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteWaitingUser(@RequestParam Long id){
        waitingListService.deleteWaitingUser(id);
    }
}
