package com.epam.ruzaliia_yakunina.java.lesson9.task9.services;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.storage.AccountMemoryStorage;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.storage.UserDAO;

/**
 * Created by Рузалия on 21.05.2016.
 */
public class UserServiceImplementation implements UserService {
    UserDAO storage;

    @Override
    public void initializeMemory() {
        storage =new AccountMemoryStorage();
        ((AccountMemoryStorage)storage).getUsers().add(new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male"));
    }

    @Override
    public UserDAO getStorage() {
        return storage;
    }

    @Override
    public void setStorage(UserDAO userDAO) {
        storage=userDAO;
    }
}
