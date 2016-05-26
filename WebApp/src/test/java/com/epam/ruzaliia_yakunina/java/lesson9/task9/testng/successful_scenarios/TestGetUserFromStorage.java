package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.successful_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test(groups = "successful")
public class TestGetUserFromStorage {
    UserService serviceMemory;

    @BeforeClass
    public void setTestData() {
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @DataProvider(name = "Data-Provider-Registered-User")
    public Object[][] parameterExistingUserTest() {
        return new Object[][]{{new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male")}};
    }

    @Test(dataProvider = "Data-Provider-Registered-User", threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testGetUserForMemory(User user) {
        long id = Thread.currentThread().getId();
        System.out.println("Thread(get memory):" + id);
        assertEquals(serviceMemory.getStorage().getUser(user.getLogin()).toString(), user.toString());
    }

    @Test(dataProvider = "Data-Provider-Registered-User", threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testGetNotRegisteredUserAndRetrieveNull(User user) {
        long id = Thread.currentThread().getId();
        System.out.println("Thread(get null):" + id);
        assertEquals(serviceMemory.getStorage().getUser(user.getEmail()), null);
    }

    @AfterClass
    public void summaryForGetUser() {
        System.out.println("Registered user was easily retrieved from both types of storages.");
    }
}
