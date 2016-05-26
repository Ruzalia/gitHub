package com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.IValidatorApp;

/**
 * Created by Рузалия on 22.05.2016.
 */
public class ErrorSetter {
    String errors = "";

    public void clear(){
        errors="";
    }

    public String setErrors(IValidatorApp validator, User user, UserService service,
                            String confirmPassword) {
        errors = validator.searchForErrors(user, service, confirmPassword);
        return errors;
    }
}
