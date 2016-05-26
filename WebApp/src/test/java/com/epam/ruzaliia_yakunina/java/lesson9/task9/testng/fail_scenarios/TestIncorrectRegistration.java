package com.epam.ruzaliia_yakunina.java.lesson9.task9.testng.fail_scenarios;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.PossibleErrorSet;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.RegistrationErrorSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Рузалия on 22.05.2016.
 */
@Test(groups = "negative")
public class TestIncorrectRegistration {
    User user;
    ErrorSetter errorSetter = new ErrorSetter();
    UserService serviceMemory;
    RegistrationErrorSet validator = new RegistrationErrorSet();

    @BeforeClass
    public void setTestData() {
        user = new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male");
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @BeforeMethod
    public void clearStringWithErrors() {
        errorSetter.clear();
    }

    @Test
    public void verifyThatErrorMessageAboutTooShortLoginIsReturnedIfUserEntersTooShortLogin() {
        user.setLogin("ok");
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, user.getPassword()), PossibleErrorSet.SHORT_LOGIN + " ");
    }

    @Test
    public void verifyThatErrorMessageAboutTooLongLoginIsReturnedIfUserEntersTooLongLogin() {
        user.setLogin("KYIVGoncharenko");
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, user.getPassword()), PossibleErrorSet.LONG_LOGIN + " ");
    }

    @Test
    public void verifyThatErrorMessageAboutAlreadyRegisteredLoginIsReturnedIfUserEntersAlreadyRegisteredLogin() {
        user.setLogin("vasya95");
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, user.getPassword()), PossibleErrorSet.LOGIN_EXISTS);
    }

    @Test
    public void verifyThatErrorMessageAboutAlreadyRegisteredLoginAndDifferentPasswordsIsReturnedIfUserEntersAlreadyRegisteredLoginAndDifferentPasswords() {
        errorSetter.clear();
        assertEquals(errorSetter.setErrors(validator, user, serviceMemory, user.getEmail()), PossibleErrorSet.DIFFERENT_PASSWORDS + " " + PossibleErrorSet.LOGIN_EXISTS);
    }

    @AfterClass
    public void summaryForIncorrectRegistrationNewUser() {
        System.out.println("The list of errors for registration forms correctly.");
    }
}
