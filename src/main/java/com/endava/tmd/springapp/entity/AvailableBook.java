package com.endava.tmd.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "available_books")
public class AvailableBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "available_book_id", nullable = false, updatable = false)
    private long availableBookId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    User owner;

    public AvailableBook(){

    }

    public long getAvailableBookId() {
        return availableBookId;
    }

    public void setAvailableBookId(long availableBookId) {
        this.availableBookId = availableBookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
