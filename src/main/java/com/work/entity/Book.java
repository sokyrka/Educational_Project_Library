package com.work.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
@Entity
public class Book {

    @Id
    private int book_id;

    private String title;
    private String author;
    private int year;
    private int pages;

    public Book(){

    }

    public Book(int book_id, String title, int year, String author, int pages) {
        this.book_id = book_id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.pages = pages;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}
