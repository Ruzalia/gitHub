package com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.RegistrationErrorSet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.ruzaliia_yakunina.java.lesson9.task9.constants.ApplicationConstants.*;

/**
 * Created by Рузалия on 17.05.2016.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    UserService serviceMemory;
    private RegistrationErrorSet validator = new RegistrationErrorSet();
    private ErrorSetter errorSetter = new ErrorSetter();
    private String errors;

    /**
     * Initializes context for servlet(creates CopyOnWriteArrayList<User> storageMemory).
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        serviceMemory = (UserServiceImplementation) getServletContext().getAttribute("UsersMemory");
    }


    /**
     * Handles POST request from client, request contains registration form data.
     * If data is valid user with such login is not registered, response will be page with congratulations,
     * if not - registration form returns with previous input data except password.
     *
     * @param request  which is received from client.
     * @param response will be returned to client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User potentialUser = new User(request.getParameter(NAME),
                request.getParameter(SURNAME), request.getParameter(PASSWORD),
                request.getParameter(EMAIL), request.getParameter(LOGIN),
                request.getParameter(GENDER));

        if (this.register(potentialUser, request.getParameter(REPEATED_PASSWORD)) == null) {
            errors = errorSetter.setErrors(validator, potentialUser, serviceMemory,
                    request.getParameter(REPEATED_PASSWORD));
            request.setAttribute("user", potentialUser);
            request.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
            return;

        } else {
            request.setAttribute("name", potentialUser.getName());
            request.setAttribute("surname", potentialUser.getSurname());
            LOGGER.info("User registered successfully and personal data is saved in collection.");
            getServletContext().getRequestDispatcher("/registrationSuccess.jsp").forward(request, response);

        }
    }

    public User register(User user, String confirmPassword) {

        LOGGER.info("In memory now:" + "\n" + serviceMemory.getStorage().toString());
        errors = errorSetter.setErrors(validator, user, serviceMemory,
                confirmPassword);

        if (!("").equals(errors)) {
            LOGGER.warn("Incorrect input: " + errors);
            LOGGER.info("Returning to registration form.");
            return null;
        } else {
            serviceMemory.getStorage().insert(user);
            LOGGER.info(user.toString() + " was added to collection.");
            return user;
        }
    }
}
