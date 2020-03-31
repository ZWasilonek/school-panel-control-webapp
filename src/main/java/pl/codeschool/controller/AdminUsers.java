package pl.codeschool.controller;

import pl.codeschool.dao.UserDao;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminUsers")
public class AdminUsers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        List<User> users = UserDao.findAll();
        if (users != null) {
            request.setAttribute("users", users);
        }

        request.getRequestDispatcher("/WEB-INF/admin-users.jsp")
                .forward(request, response);
    }
}
