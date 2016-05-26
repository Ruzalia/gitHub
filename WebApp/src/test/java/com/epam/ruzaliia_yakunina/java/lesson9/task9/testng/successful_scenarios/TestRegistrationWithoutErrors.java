package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.successful_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.RegistrationErrorSet;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test(groups = "successful")
public class TestRegistrationWithoutErrors {
    User user;
    ErrorSetter errorSetter;
    UserService serviceMemory;
    RegistrationErrorSet validator = new RegistrationErrorSet();

    @BeforeClass
    public void setTestData() {
        Integer temp = ((int) (Math.random() * 10000000));
        user = new User("epam", "systems", "1234", "epam@gmail.com", "e" + temp.toString(), "male");
        errorSetter = new ErrorSetter();
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();

    }


    @Test(threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testCorrectRegistration() {
        errorSetter.clear();
        long id = Thread.currentThread().getId();
        System.out.println("Thread(registration): " + id);
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, user.getPassword()), "");
    }

    @AfterTest
    public void deleteTestUserFromStorage() {
        System.out.println(" Test user was inserted into database and after that was deleted.");
    }
}
