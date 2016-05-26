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
public class TestExistsForStorage {
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

    @Test(dataProvider = "Data-Provider-Registered-User", threadPoolSize = 3, invocationCount = 1, timeOut = 10000)
    public void testExistsForMemory(User user) {
        long id = Thread.currentThread().getId();
        System.out.println("Thread(exists memory):" + id);
        assertEquals(serviceMemory.getStorage().exists(user.getLogin(), user.getPassword()), true);
    }

    @AfterClass
    public void summaryForExistsInStorage() {
        System.out.println(" Registered user was found by function exists() in database and in memory.");
    }
}
