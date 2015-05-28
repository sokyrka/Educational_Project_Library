package com.work.entity;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by Eugine Sokirka on 28.05.2015.
 */
@Entity
@Table(name = "LUSER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "SEQ_LUSER", allocationSize = 1)
    private int user_id;

    private String first_name;
    private String second_name;
    private String login;
    private String password;

    public User(){

    }

    public User(String first_name, String second_name, String login, String password) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.login = login;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
