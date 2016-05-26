package com.epam.ruzaliia_yakunina.java.lesson9.task9.validator;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Рузалия on 17.05.2016.
 */
public class RegistrationErrorSet extends PossibleErrorSet {
    private final Logger LOGGER = Logger.getLogger(RegistrationErrorSet.class);

    /**
     * Verifies if data is valid to register user(if such login is not registered and password is similar to password confirmation).
     *
     * @param user             potential object to be added to storage
     * @param service          collection with all users
     * @param repeatedPassword password confirmation
     * @return allErrors String with all input errors
     */
    @Override
    public String searchForErrors(User user, UserService service, String repeatedPassword) {
        allErrors = "";
        if(user.getLogin().length()<3){
            allErrors = allErrors + SHORT_LOGIN + " ";
            LOGGER.warn("Error detected: " + allErrors);
        }
        if(user.getLogin().length()>10){
            allErrors = allErrors + LONG_LOGIN + " ";
            LOGGER.warn("Error detected: " + allErrors);
        }
        if (!user.getPassword().equals(repeatedPassword)) {
            allErrors = allErrors + DIFFERENT_PASSWORDS + " ";
            LOGGER.warn("Error detected: " + allErrors);
        }
        try {
            if (service.getStorage().getUser(user.getLogin()).getLogin().equals(user.getLogin())) {
                allErrors = allErrors + LOGIN_EXISTS;
                LOGGER.warn("Error detected: " + LOGIN_EXISTS);
            }
        } catch (NullPointerException e) {
            LOGGER.info("User can be registered, such login is not registered.");
        }

        return allErrors;

    }
}
