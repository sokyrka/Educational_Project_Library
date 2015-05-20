package com.work.common;

/**
 * Created by Eugine Sokirka on 19.05.2015.
 */
public class User {
    private final String first_name;
    private final String second_name;
    private final String login;
    private final String password;

    private User(Builder builder){
        this.first_name = builder.first_name;
        this.second_name = builder.second_name;
        this.login = builder.login;
        this.password = builder.password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (first_name != null ? !first_name.equals(user.first_name) : user.first_name != null) return false;
        if (second_name != null ? !second_name.equals(user.second_name) : user.second_name != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (second_name != null ? second_name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static class Builder{
        private String first_name;
        private String second_name;
        private String login;
        private String password;

        public Builder(){}

        public Builder(User user){
            this.first_name = user.first_name;
            this.second_name = user.second_name;
            this.login = user.login;
        }

        public Builder first_name(String first_name){
            this.first_name = first_name;
            return this;
        }

        public Builder second_name(String second_name){
            this.second_name = second_name;
            return this;
        }

        public Builder login(String login){
            this.login = login;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
