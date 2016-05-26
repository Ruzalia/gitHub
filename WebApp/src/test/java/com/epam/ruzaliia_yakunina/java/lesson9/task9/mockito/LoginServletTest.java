package com.epam.ruzaliia_yakunina.java.lesson9.task9.mockito;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.constants.ApplicationConstants;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.LoginServlet;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Рузалия on 23.05.2016.
 */
public class LoginServletTest extends Mockito {
    UserService service;

    @BeforeClass
    public void beforeClass() {
        service = new UserServiceImplementation();
        service.initializeMemory();
    }

    @Test
    public void doPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        User user = new User("test", "test");

        when(context.getAttribute("UsersDB")).thenReturn(service);

        when(context.getRequestDispatcher("/authorization.jsp")).thenReturn(dispatcher);

        when(request.getParameter(ApplicationConstants.LOGIN)).thenReturn(user.getLogin());
        when(request.getParameter(ApplicationConstants.PASSWORD)).thenReturn(user.getPassword());

        when(config.getServletContext()).thenReturn(context);

        LoginServlet servlet = new LoginServlet();
        servlet.init(config);
        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter(ApplicationConstants.LOGIN);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.PASSWORD);
    }
}
