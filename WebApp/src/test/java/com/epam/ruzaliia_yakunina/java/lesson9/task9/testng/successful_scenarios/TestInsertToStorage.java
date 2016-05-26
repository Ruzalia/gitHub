package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.successful_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test
public class TestInsertToStorage {
    UserService serviceMemory;
    String login = "";

    @BeforeClass
    public void setTestData() {
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @DataProvider(name = "Data-Provider-New-User")
    public Object[][] parameterExistingUserTest() {
        Integer temp = ((int) (Math.random() * 1000000));
        return new Object[][]{{new User("cat", "cat", "1111", "cat@gmail.com", temp.toString(), "male")}};
    }

    @Test(dataProvider = "Data-Provider-New-User", threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testInsertForMemory(User user) {
        login = user.getLogin();
        long id = Thread.currentThread().getId();
        System.out.println("Thread(insert memory):" + id);
        serviceMemory.getStorage().insert(user);
        assertEquals(serviceMemory.getStorage().exists(user.getLogin(), user.getPassword()), true);
    }

    @AfterMethod
    public void deleteTestUserFromStorage() {
        System.out.println(" Test user was successfully inserted to storage and after that was deleted from storage.");
    }

}
