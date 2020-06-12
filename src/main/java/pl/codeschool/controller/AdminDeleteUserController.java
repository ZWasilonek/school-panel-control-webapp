package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.dao.UserDao;
import pl.codeschool.model.Admin;
import pl.codeschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete/user")
public class AdminDeleteUserController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDeleteUserController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserId = request.getParameter("userId");

        if (paramUserId != null && !"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                User foundedUser = UserDao.read(userId);
                if (foundedUser != null && !foundedUser.getUserName().equals(Admin.getAdminUsername())) {
                    UserDao.delete(userId);
                } else
                    request.setAttribute("tryingDeleteSuperAdmin", true);

            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/admin/users")
                .forward(request, response);
    }

}