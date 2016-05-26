package com.epam.ruzaliia_yakunina.java.lesson9.task9.easymock;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.IValidatorApp;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.easymock.IMocksControl;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.expect;

/**
 * Created by Рузалия on 23.05.2016.
 */
public class LoginErrorSetterTest extends EasyMockSupport {
    UserService serviceMemory = new UserServiceImplementation();
    ErrorSetter errorSetter = new ErrorSetter();

    @Test
    public void verifyThatMethodSetErrorsInLoginServletReturnsAnEmptyStringForRegisteredUserData() {
        serviceMemory.initializeMemory();
        IMocksControl control = EasyMock.createControl();
        IValidatorApp validator = control.createMock(IValidatorApp.class);
        User user = new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "vasya95", "male");
        expect(errorSetter.setErrors(validator, user, serviceMemory, "")).andReturn("").atLeastOnce();
        control.replay();
        errorSetter.setErrors(validator, user, serviceMemory, "");
        control.verify();
        System.out.println("ErrorSetter with validator inside works correctly for authorization.");
    }
}
