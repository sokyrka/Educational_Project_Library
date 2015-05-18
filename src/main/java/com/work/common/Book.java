package com.work.common;

/**
 * Created by Eugine Sokirka on 15.05.2015.
 */
public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final int pages;

    private Book(Builder builder){
        this.title = builder.title;
        this.author = builder.author;
        this.year = builder.year;
        this.pages = builder.pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (pages != book.pages) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return !(author != null ? !author.equals(book.author) : book.author != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + pages;
        return result;
    }

    public static class Builder{
        private String title;
        private String author;
        private int year;
        private int pages;

        public Builder(){}

        public Builder(Book book){
            this.title = book.title;
            this.author = book.author;
            this.year = book.year;
            this.pages = book.pages;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder year(int year){
            this.year = year;
            return this;
        }

        public Builder pages(int pages){
            this.pages = pages;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }
}
