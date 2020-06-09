package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/group/users/info")
public class InfoGroupUsersController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupName = request.getParameter("group");
        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId) &&
                paramGroupName != null && !"".equals(paramGroupName)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                if (GroupDao.read(groupId) != null) {
                    List<User> users = UserDao.findAllByGroupId(groupId);
                    request.setAttribute("groupName", paramGroupName);
                    request.setAttribute("users", users);
                } else request.setAttribute("groupNotExists", true);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/group-users.jsp")
                .forward(request, response);
    }

}
