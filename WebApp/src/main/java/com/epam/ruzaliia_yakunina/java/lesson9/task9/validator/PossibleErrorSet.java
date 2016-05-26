package com.epam.ruzaliia_yakunina.java.lesson9.task9.validator;

/**
 * Created by Рузалия on 17.05.2016.
 */
public abstract class PossibleErrorSet implements IValidatorApp {
    public static final String LOGIN_EXISTS = "User with such login is already registered.";
    public static final String DIFFERENT_PASSWORDS = "Passwords are different.";
    public static final String LOGIN_ABSENT = "User with such login is not registered.";
    public static final String INCORRECT_PASSWORD = "Incorrect password.";
    public static final String SHORT_LOGIN = "Login too short(Login name should contain from 3 to 10 characters).";
    public static final String LONG_LOGIN = "Login too long(Login name should contain from 3 to 10 characters).";
    public static String allErrors;
}
