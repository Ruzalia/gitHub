package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.successful_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.storage.AccountMemoryStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test
public class TestInitializeForService {
    AccountMemoryStorage accountMemoryStorage;
    UserService serviceMemory;
    String login = "vasya95";

    @BeforeClass
    public void setTestData() {
        serviceMemory = new UserServiceImplementation();

        accountMemoryStorage = new AccountMemoryStorage();
        accountMemoryStorage.getUsers().add(new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male"));
    }

    @Test(threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testInitializeForMemory() {
        long id = Thread.currentThread().getId();
        System.out.println("Thread(initialize memory): " + id);
        serviceMemory.initializeMemory();
        assertEquals(serviceMemory.getStorage().getUser(login).toString(),
                accountMemoryStorage.getUsers().get(0).toString());
    }

    @AfterClass
    public void summaryForInitializing() {
        System.out.println("All storages initialized successfully.");
    }
}
