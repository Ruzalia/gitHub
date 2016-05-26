package com.epam.ruzaliia_yakunina.java.lesson9.task9.storage;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;

/**
 * Created by Рузалия on 17.05.2016.
 */
public interface UserDAO {
    void insert(User user);

    boolean exists(String login, String password);

    User getUser(String login);
}
