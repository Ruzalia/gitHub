package com.epam.ruzaliia_yakunina.java.lesson9.task9.validator;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;

public interface IValidatorApp {

    String searchForErrors(User user, UserService service, String repeatedPassword);
}
