package com.epam.ruzaliia_yakunina.java.lesson9.task9.storage;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Ruzaliia_Yakunina on 5/17/2016.
 */
public class AccountMemoryStorage implements UserDAO {
    CopyOnWriteArrayList<User> users;

    public CopyOnWriteArrayList<User> getUsers() {
        return users;
    }

    public AccountMemoryStorage() {
        users = new CopyOnWriteArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (User user : users) {
            builder.append(user.toString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public void insert(User user) {
        users.add(user);
    }

    /**
     * Verifies if user with login and password exists in storage.
     *
     * @param login
     * @param password
     * @return
     */
    @Override
    public boolean exists(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) & user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}
