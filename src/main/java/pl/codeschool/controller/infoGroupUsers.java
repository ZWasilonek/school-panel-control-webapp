package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Solution;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/infoGroupUsers")
public class infoGroupUsers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String paramGroupName = request.getParameter("group");
        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId) &&
            paramGroupName != null && !"".equals(paramGroupName)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                List<User> users = UserDao.findAllByGroupId(groupId);

                request.setAttribute("groupName", paramGroupName);
                request.setAttribute("users", users);
                request.getRequestDispatcher("/WEB-INF/group-users.jsp")
                        .forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }
}
