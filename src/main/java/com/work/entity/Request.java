package com.work.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
@Entity
public class Request {

    @Id
    private int request_id;

    private int book_id;
    private int user_id;
    private boolean done;
    private boolean home;
    private boolean library;

    public Request(){

    }

    public Request(int request_id, int book_id, int user_id, boolean done, boolean home, boolean library) {
        this.request_id = request_id;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public boolean isLibrary() {
        return library;
    }

    public void setLibrary(boolean library) {
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
