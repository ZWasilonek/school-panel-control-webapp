package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Admin;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/groups")
public class AdminGroupsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Group> groups = GroupDao.findAll();
        if (groups != null && groups.size() != 0) {
            request.setAttribute("groups", groups);
            request.setAttribute("ADMIN_GROUP", Admin.getAdminGroup());
            isADMIN(request);
        } else request.setAttribute("groupsNotFound", true);

        request.getRequestDispatcher("/WEB-INF/admin-groups.jsp")
                .forward(request, response);
    }

    private void isADMIN(HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
        if (adminId != null) {
            User admin = UserDao.read(adminId);
            if (admin != null && admin.getUserName().equals(Admin.getAdminUsername())) {
                request.setAttribute("ADMIN_USERNAME", true);
            }
        }
    }

}