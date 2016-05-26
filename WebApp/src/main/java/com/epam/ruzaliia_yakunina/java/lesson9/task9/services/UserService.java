package com.epam.ruzaliia_yakunina.java.lesson9.task9.services;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.storage.UserDAO;

/**
 * Created by Рузалия on 21.05.2016.
 */
public interface UserService {
    void initializeMemory();
    UserDAO getStorage();
    void setStorage(UserDAO userDAO);
}
