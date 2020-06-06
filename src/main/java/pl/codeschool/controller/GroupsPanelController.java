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

@WebServlet("/groupsPanel")
public class GroupsPanelController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Group> allGroups = GroupDao.findAll();
        if (allGroups != null && allGroups.size() != 0) {
            request.setAttribute("groups", allGroups);
        }

        request.getRequestDispatcher("WEB-INF/groups.jsp")
                .forward(request, response);
    }

}
