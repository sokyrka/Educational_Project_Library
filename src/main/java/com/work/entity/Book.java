package com.work.entity;

import javax.persistence.*;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "SEQ_BOOK", allocationSize = 1)
    private int book_id;

    private String title;
    private String author;
    private int year;
    private int pages;
    private int busy;

    public Book(){

    }

    public Book(String title, String author, int year, int pages, int busy) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.busy = busy;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", busy=" + busy +
                '}';
    }
}
