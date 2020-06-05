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

@WebServlet("/adminAddUserToGroup")
public class AdminAddUserToGroup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupId = request.getParameter("groupId");
        String paramUserId = request.getParameter("userId");

        Group foundedGroup = null;
        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                foundedGroup = GroupDao.read(groupId);
                request.setAttribute("group", foundedGroup);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (foundedGroup != null && paramUserId != null && !"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                User founded = UserDao.read(userId);
                founded.setGroup(foundedGroup);
                UserDao.update(founded);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        getAllUsers(request);

        request.getRequestDispatcher("/WEB-INF/admin-add-user-to-group.jsp")
                .forward(request, response);
    }

    private void getAllUsers(HttpServletRequest request) {
        List<User> users = UserDao.findAll();
        if (users != null) {
            request.setAttribute("users", users);
        }
    }

}