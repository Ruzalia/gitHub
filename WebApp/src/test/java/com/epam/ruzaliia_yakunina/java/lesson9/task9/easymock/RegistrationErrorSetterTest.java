package com.epam.ruzaliia_yakunina.java.lesson9.task9.easymock;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.IValidatorApp;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.expect;

/**
 * Created by Рузалия on 23.05.2016.
 */
public class RegistrationErrorSetterTest {
    UserService serviceMemory = new UserServiceImplementation();
    ErrorSetter errorSetter = new ErrorSetter();

    @Test
    public void verifyThatMethodSetErrorsInRegisterServletReturnsAnEmptyStringForNotRegisteredUserValidData() {
        serviceMemory.initializeMemory();
        IMocksControl control = EasyMock.createControl();
        IValidatorApp validator = control.createMock(IValidatorApp.class);
        Integer temp = ((int) (Math.random() * 10000000));
        User user = new User("Vasya", "Nikolaev", "1234", "nikolaev@gmail.com", "v" + temp.toString(), "male");
        expect(errorSetter.setErrors(validator, user, serviceMemory, user.getPassword())).andReturn("").atLeastOnce();
        control.replay();
        errorSetter.setErrors(validator, user, serviceMemory, user.getPassword());
        control.verify();
        System.out.println("ErrorSetter with validator inside works correctly for registration.");
    }
}
