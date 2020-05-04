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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminEditUser")
public class AdminEditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String userName = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPass");
        String repassword = request.getParameter("rePass");
        String group = request.getParameter("groupName");

        User user = (User) request.getSession().getAttribute("user");
        int userId = 0;
        if (user != null) {
            userId = user.getId();
        }

        if (userName != null && !"".equals(userName) && email != null && !"".equals(email) &&
                password != null && !"".equals(password) && group != null && !"".equals(group) &&
                repassword != null && !"".equals(repassword) && password.equals(repassword) &&
                userId > 0) {

            Group selectedGroup = GroupDao.readByName(group);
            User updatedUser = new User(userId, userName, email, password, selectedGroup);
            UserDao.update(updatedUser);

            //nie wyświetla się info o dodaniu użytkownika
            request.setAttribute("isUpdated", true);
            response.sendRedirect("/adminEditUser");

        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramUserId = request.getParameter("userId");

        if (paramUserId != null && !"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                User founded = UserDao.read(userId);
                request.getSession().setAttribute("user", founded);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Group> groups = GroupDao.findAll();
        if (groups != null) {
            request.setAttribute("groups", groups);
        }

        request.getRequestDispatcher("/WEB-INF/admin-edit-user.jsp")
                .forward(request, response);
    }
}
