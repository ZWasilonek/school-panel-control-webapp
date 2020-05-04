package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminAddGroup")
public class AdminAddGroup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String groupName = request.getParameter("groupName");

        if (groupName != null && !"".equals(groupName)) {

            Group newGroup = new Group(groupName);
            GroupDao.create(newGroup);

            //nie wyświetla się info o dodaniu użytkownika
            request.setAttribute("groupCreated", true);
            request.getRequestDispatcher("/WEB-INF/admin-add-group.jsp")
                    .forward(request, response);
        } else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.getRequestDispatcher("/WEB-INF/admin-add-group.jsp")
                .forward(request, response);
    }
}
