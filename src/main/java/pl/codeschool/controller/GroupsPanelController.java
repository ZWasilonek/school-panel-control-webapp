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

@WebServlet("/groups/panel")
public class GroupsPanelController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Group> allGroups = GroupDao.findAll();
        if (allGroups != null && allGroups.size() != 0) {
            allGroups.sort((g1, g2) -> g1.getName().compareToIgnoreCase(g2.getName()));
            Integer adminId = (Integer) request.getSession().getAttribute("adminId");
            if (adminId == null) {
                String adminGroupName = "ADMIN Group";
                allGroups.remove(GroupDao.readByName(adminGroupName));
            }
            request.setAttribute("groups", allGroups);
        }

        request.getRequestDispatcher("/WEB-INF/groups.jsp")
                .forward(request, response);
    }

}