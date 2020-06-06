package pl.codeschool.controller;

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

@WebServlet("/infoUser")
public class InfoUserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramUserId = request.getParameter("userId");

        if (paramUserId != null && !"".equals(paramUserId)) {
            try {
                int userId = Integer.parseInt(paramUserId);
                User user = UserDao.read(userId);

                if (user != null) {
                    request.setAttribute("user", user);
                    List<Solution> userSolutions = SolutionDao.findAllByUserId(userId);
                    request.setAttribute("userSolutions", userSolutions);

                } else request.setAttribute("userNotExists", true);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/user-info.jsp")
                .forward(request, response);
    }

}
