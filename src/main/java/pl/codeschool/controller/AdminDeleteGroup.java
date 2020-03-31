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

@WebServlet("/adminDeleteGroup")
public class AdminDeleteGroup extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);

                //Sprawdzamy czy grupa jest pusta, nie chcemy usuwać grupy z użytkownikami
                List<User> checksIsEmpty = UserDao.findAllByGroupId(groupId);
                if (checksIsEmpty.size() == 0 || checksIsEmpty == null) {
                    GroupDao.delete(groupId);
                    request.getRequestDispatcher("/adminGroups")
                            .forward(request, response);
                } else {
                    request.setAttribute("isEmpty", false);
                    request.getRequestDispatcher("/adminGroups")
                            .forward(request, response);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/adminGroups")
                .forward(request, response);
    }
}
