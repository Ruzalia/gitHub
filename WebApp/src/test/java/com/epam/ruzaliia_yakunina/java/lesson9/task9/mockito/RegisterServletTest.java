package com.epam.ruzaliia_yakunina.java.lesson9.task9.mockito;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.bean.User;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.constants.ApplicationConstants;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.controllers.RegisterServlet;
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
public class RegisterServletTest extends Mockito {
    UserService serviceDB, serviceMemory;

    @BeforeClass
    public void beforeClass() {
        serviceMemory = new UserServiceImplementation();
        serviceMemory.initializeMemory();
    }

    @Test
    public void doPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        User user = new User("test", "test", "test", "test", "test", "test");

        when(context.getAttribute("UsersMemory")).thenReturn(serviceMemory);

        when(context.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);

        when(request.getParameter(ApplicationConstants.NAME)).thenReturn(user.getName());
        when(request.getParameter(ApplicationConstants.SURNAME)).thenReturn(user.getSurname());
        when(request.getParameter(ApplicationConstants.PASSWORD)).thenReturn(user.getPassword());
        when(request.getParameter(ApplicationConstants.EMAIL)).thenReturn(user.getEmail());
        when(request.getParameter(ApplicationConstants.LOGIN)).thenReturn(user.getLogin());
        when(request.getParameter(ApplicationConstants.GENDER)).thenReturn(user.getGender());

        when(config.getServletContext()).thenReturn(context);

        RegisterServlet servlet = new RegisterServlet();
        servlet.init(config);
        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter(ApplicationConstants.NAME);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.SURNAME);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.PASSWORD);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.EMAIL);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.LOGIN);
        verify(request, atLeast(1)).getParameter(ApplicationConstants.GENDER);
    }
}
