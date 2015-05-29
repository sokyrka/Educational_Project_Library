package com.work.entity;

import javax.persistence.*;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
@Entity
@Table(name = "REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id_sequence")
    @SequenceGenerator(name = "request_id_sequence", sequenceName = "SEQ_REQUEST", allocationSize = 1)
    private int request_id;

    private int book_id;
    private int user_id;
    private int done;
    private int home;
    private int library;

    public Request(){

    }

    public Request(int book_id, int user_id, int done, int home, int library) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.done = done;
        this.home = home;
        this.library = library;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getLibrary() {
        return library;
    }

    public void setLibrary(int library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request_id=" + request_id +
                ", book_id=" + book_id +
                ", user_id=" + user_id +
                ", done=" + done +
                ", home=" + home +
                ", library=" + library +
                '}';
    }
}
