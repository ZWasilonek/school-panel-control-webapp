package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminEditGroup")
public class AdminEditGroup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String groupName = request.getParameter("groupName");

        Group group = (Group) request.getSession().getAttribute("group");
        int groupId = 0;
        if (group != null) {
            groupId = group.getId();
        }

        if (groupName != null && !"".equals(groupName)) {

            Group updatedGroup = new Group(groupId, groupName);
            GroupDao.update(updatedGroup);

            //nie wyświetla się info o dodaniu użytkownika
            request.setAttribute("isUpdated", true);
            request.getRequestDispatcher("/WEB-INF/admin-edit-group.jsp")
                    .forward(request, response);

        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                Group founded = GroupDao.read(groupId);
                request.getSession().setAttribute("group", founded);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-group.jsp")
                .forward(request, response);
    }
}
