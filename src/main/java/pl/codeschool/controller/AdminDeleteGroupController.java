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

@WebServlet("/admin/delete/group")
public class AdminDeleteGroupController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);

                List<User> foundedUsers = UserDao.findAllByGroupId(groupId);
                if (foundedUsers.size() == 0) {
                    GroupDao.delete(groupId);
                } else {
                    request.setAttribute("isEmpty", false);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/adminGroups")
                .forward(request, response);
    }

}
