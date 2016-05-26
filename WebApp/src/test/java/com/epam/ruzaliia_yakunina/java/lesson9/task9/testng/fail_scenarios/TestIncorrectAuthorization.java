package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.fail_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.AuthorizationErrorSet;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.PossibleErrorSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test(groups = "negative")
public class TestIncorrectAuthorization {
    User user1, user2;
    ErrorSetter errorSetter;
    UserService serviceMemory;
    AuthorizationErrorSet validator = new AuthorizationErrorSet();

    @BeforeClass
    public void setTestData() {
        Integer temp = ((int) (Math.random() * 10000000));
        user1 = new User("Vasya", "Nikolaev", "1234" + temp.toString(), "nikolaev@gmail.com", "vasya95", "male");
        user2 = new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95" + temp.toString(), "male");
        errorSetter = new ErrorSetter();
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @Test
    public void verifyThatErrorMessageAboutIncorrectPasswordIsReturnedIfRegisteredUserEntersIncorrectPassword() {
        errorSetter.clear();
        assertEquals(errorSetter.setErrors(validator, user1, serviceMemory, ""), PossibleErrorSet.INCORRECT_PASSWORD+" ");
    }

    @Test
    public void verifyThatErrorMessageAboutNotRegisteredLoginIsReturnedIfUserEntersNotRegisteredLogin() {
        errorSetter.clear();
        assertEquals(errorSetter.setErrors(validator, user2, serviceMemory, ""), PossibleErrorSet.LOGIN_ABSENT);
    }

    @AfterClass
    public void summaryForAuthorizationNotRegisteredUser() {
        System.out.println("The list of errors for authorization not registered user forms correctly.");
    }
}
