package com.epam.ruzaliia_yakunina.java.lesson9.task9.validator;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Рузалия on 17.05.2016.
 */
public class AuthorizationErrorSet extends PossibleErrorSet {
    private final Logger LOGGER = Logger.getLogger(AuthorizationErrorSet.class);

    /**
     * Verifies if data is valid to authorize user(if such login is registered and password is correct).
     *
     * @param user potential object to be retrieved from storage
     * @param service collection with all users
     * @param repeatedPassword password confirmation
     * @return allErrors String with all input errors
     */
    @Override
    public String searchForErrors(User user, UserService service, String repeatedPassword) {
        allErrors = "";
        try {
            service.getStorage().getUser(user.getLogin());
            if (!user.getPassword()
                    .equals(service.getStorage().getUser(user.getLogin()).getPassword())) {
                allErrors = allErrors + INCORRECT_PASSWORD + " ";
                LOGGER.warn("Error detected: "+allErrors);
            }
        } catch (NullPointerException e) {
            allErrors = allErrors + LOGIN_ABSENT;
            LOGGER.warn("Error detected: "+LOGIN_ABSENT);
        }
        return allErrors;

    }
}
