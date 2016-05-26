package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.successful_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.AuthorizationErrorSet;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test(groups = "successful")
public class TestAuthorizationWithoutErrors {
    User user;
    ErrorSetter errorSetter;
    UserService serviceMemory;
    AuthorizationErrorSet validator = new AuthorizationErrorSet();

    @BeforeClass
    public void setTestData() {
        user = new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male");
        errorSetter = new ErrorSetter();
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @Test(threadPoolSize = 4, invocationCount = 1, timeOut = 10000)
    public void testCorrectAuthorization() {
        errorSetter.clear();
        long id = Thread.currentThread().getId();
        System.out.println("Thread(authorization): " + id);
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, ""), "");
    }


    @AfterTest
    public void summaryForAuthorizationRegisteredUser() {
        System.out.println("The list of errors for authorization registered user is empty.");
    }
}
