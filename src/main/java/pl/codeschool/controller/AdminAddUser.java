package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminAddUser")
public class AdminAddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String userName = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPass");
        String repassword = request.getParameter("rePass");
        String group = request.getParameter("groupName");

        if (userName != null && !"".equals(userName) && email != null && !"".equals(email) &&
            password != null && !"".equals(password) && group != null && !"".equals(group) &&
            repassword != null && !"".equals(repassword) && password.equals(repassword)) {

            Group selectedGroup = GroupDao.readByName(group);
            User newUser = new User(userName, email, password, selectedGroup);
            UserDao.create(newUser);

            //nie wyświetla się info o dodaniu użytkownika
            request.setAttribute("userCreated", true);
            response.sendRedirect("/adminAddUser");
        } else {
            doGet(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        List<Group> groups = GroupDao.findAll();
        if (groups != null) {
            request.setAttribute("groups", groups);
            request.getRequestDispatcher("/WEB-INF/admin-add-user.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("hasGroups", true);
        }

    }
}
