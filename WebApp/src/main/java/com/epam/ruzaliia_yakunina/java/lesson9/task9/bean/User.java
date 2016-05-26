package com.epam.ruzaliia_yakunina.java.lesson9.task9.bean;

/**
 * Created by Рузалия on 16.05.2016.
 */
public class User {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String login;
    private String gender;

    public User(String login, String pass) {
        name = null;
        surname = null;
        password = pass;
        email = null;
        this.login = login;
        gender = null;
    }

    public User(String name, String surname, String pass, String mail, String login, String gender) {
        this.name = name;
        this.surname = surname;
        this.password = pass;
        email = mail;
        this.login = login;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname + " E-mail: " + email + " Login: " + login + " Gender: " + gender;
    }
}
