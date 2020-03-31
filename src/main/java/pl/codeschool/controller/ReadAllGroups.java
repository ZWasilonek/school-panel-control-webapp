package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/groups")
public class ReadAllGroups extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        List<Group> allGroups = GroupDao.findAll();
        request.setAttribute("groups", allGroups);

        request.getRequestDispatcher("WEB-INF/groups.jsp")
                .forward(request, response);
//        if (paramGroupId != null && !"".equals(paramGroupId)) {
//        }

    }
}
