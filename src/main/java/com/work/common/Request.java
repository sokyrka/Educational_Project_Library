package com.work.common;

/**
 * Created by Eugine Sokirka on 25.05.2015.
 */
public class Request {
    private final int request_id;
    private final int book_id;
    private final int user_id;
    private final boolean done;
    private final boolean home;
    private final boolean library;

    private Request(Builder builder){
        this.request_id = builder.request_id;
        this.book_id = builder.book_id;
        this.user_id = builder.user_id;
        this.done = builder.done;
        this.home = builder.home;
        this.library = builder.library;
    }

    public int getRequest_id() {
        return request_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isHome() {
        return home;
    }

    public boolean isLibrary() {
        return library;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (request_id != request.request_id) return false;
        if (book_id != request.book_id) return false;
        if (user_id != request.user_id) return false;
        if (done != request.done) return false;
        if (home != request.home) return false;
        return library == request.library;

    }

    @Override
    public int hashCode() {
        int result = request_id;
        result = 31 * result + book_id;
        result = 31 * result + user_id;
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + (home ? 1 : 0);
        result = 31 * result + (library ? 1 : 0);
        return result;
    }

    public static class Builder{
        private int request_id;
        private int book_id;
        private int user_id;
        private boolean done;
        private boolean home;
        private boolean library;

        public Builder(){}

        public Builder(Request request){
            this.request_id = request.request_id;
            this.book_id = request.book_id;
            this.user_id = request.user_id;
            this.done = request.done;
            this.home = request.home;
            this.library = request.library;
        }

        public Builder request_id(int request_id){
            this.request_id = request_id;
            return this;
        }

        public Builder book_id(int book_id){
            this.book_id = book_id;
            return this;
        }

        public Builder user_id(int user_id){
            this.user_id = user_id;
            return this;
        }

        public Builder done(boolean done){
            this.done = done;
            return this;
        }

        public Builder home(boolean home){
            this.home = home;
            return this;
        }

        public Builder library(boolean library){
            this.library = library;
            return this;
        }

        public Request build(){
            return new Request(this);
        }
    }
}
