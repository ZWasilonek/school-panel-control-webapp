package pl.codeschool.controller;

import pl.codeschool.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminDeleteUser")
public class AdminDeleteUserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserId = request.getParameter("userId");

        if (paramUserId != null && !"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                UserDao.delete(userId);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/adminUsers")
                .forward(request, response);
    }

}
