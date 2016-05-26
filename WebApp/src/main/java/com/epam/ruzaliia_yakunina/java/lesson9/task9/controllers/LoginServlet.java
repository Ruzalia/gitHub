package com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.util.ErrorSetter;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.validator.AuthorizationErrorSet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.ruzaliia_yakunina.java.lesson9.task9.constants.ApplicationConstants.LOGIN;
import static com.epam.ruzaliia_yakunina.java.lesson9.task9.constants.ApplicationConstants.PASSWORD;

/**
 * Created by Рузалия on 16.05.2016.
 */
@WebServlet("/authorize")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private UserService serviceMemory;
    private AuthorizationErrorSet validator = new AuthorizationErrorSet();
    private ErrorSetter errorSetter = new ErrorSetter();
    private String errors;

    /**
     * Initializes context for servlet(creates CopyOnWriteArrayList<User> serviceMemory).
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        serviceMemory = (UserServiceImplementation) getServletContext().getAttribute("UsersMemory");
    }


    /**
     * Handles POST request from client, request contains authorization form data.
     * If data is valid and user with such login and password is registered,
     * response will be page with greeting, if not -
     * authorization form returns with previous input data except password.
     *
     * @param request  which is received from client.
     * @param response will be returned to client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User notUser = new User(request.getParameter(LOGIN), request.getParameter(PASSWORD));
        if(("").equals(this.authorize(notUser))){
        errors = errorSetter.setErrors(validator, notUser, serviceMemory, "");
            LOGGER.warn("Incorrect input: " + errors);
            LOGGER.info("Returning to authorization form.");
            request.setAttribute("user", notUser);
            request.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/authorization.jsp").forward(request, response);
            return;
        }

        else {
                User user = serviceMemory.getStorage().getUser(request.getParameter(LOGIN));
                request.setAttribute("name", user.getName());
                request.setAttribute("surname", user.getSurname());
            LOGGER.info("User authorized successfully.");

                getServletContext().getRequestDispatcher("/authorizationSuccess.jsp")
                        .forward(request, response);
                return;
            }
        }

        public String authorize(User user){
            String userLogin= "";
            errors = errorSetter.setErrors(validator, user, serviceMemory, "");
            if (!errors.equals("")) {
                return userLogin;
            } else {
                    return user.getLogin();
            }

    }

}
