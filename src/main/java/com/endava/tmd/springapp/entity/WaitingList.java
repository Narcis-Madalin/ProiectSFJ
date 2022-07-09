package com.endava.tmd.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "waiting_list")
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "SERIAL")
    private Long waitingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User waitingUser;

    @Column(name = "waiting_number")
    private Long waitingNumber;

    public WaitingList(){

    }

    public Long getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(Long waitingId) {
        this.waitingId = waitingId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getWaitingUser() {
        return waitingUser;
    }

    public void setWaitingUser(User waitingUser) {
        this.waitingUser = waitingUser;
    }

    public Long getWaitingNumber() {
        return waitingNumber;
    }

    public void setWaitingNumber(Long waitingNumber) {
        this.waitingNumber = waitingNumber;
    }
}
