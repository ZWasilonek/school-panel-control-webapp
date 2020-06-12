package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@WebServlet("/admin/delete/group")
public class AdminDeleteGroupController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDeleteGroupController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupId = request.getParameter("groupId");

        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                Group foundedGroup = GroupDao.read(groupId);

                List<User> foundedUsers = UserDao.findAllByGroupId(groupId);
                if (foundedGroup != null && foundedGroup.getName().equals(Admin.getAdminGroup()))
                    request.setAttribute("tryingAdminGroupNameDelete", true);
                else if (foundedUsers == null || foundedUsers.size() != 0)
                    request.setAttribute("isEmpty", false);

                else GroupDao.delete(groupId);

            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/admin/groups")
                .forward(request, response);
    }

}