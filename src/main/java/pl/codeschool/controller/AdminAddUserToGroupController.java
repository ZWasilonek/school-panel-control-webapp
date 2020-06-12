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

@WebServlet("/admin/add/userToGroup")
public class AdminAddUserToGroupController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAddUserToGroupController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupId = request.getParameter("groupId");
        String paramUserId = request.getParameter("userId");

        Group foundedGroup = null;
        if (paramGroupId != null && !"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                foundedGroup = GroupDao.read(groupId);
                request.setAttribute("group", foundedGroup);
                request.setAttribute("ADMIN_USERNAME", Admin.getAdminUsername());
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }

        if (foundedGroup != null) {
            if (paramUserId != null && !"".equals(paramUserId)) {
                try {
                    int userId = Integer.parseInt(paramUserId);
                    User founded = UserDao.read(userId);
                    if (founded != null) {
                        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
                        if (adminId != null) {
                            //If admin is moved to a group other than the ADMIN group, it will lose its administrative privileges
                            founded.setAdmin(foundedGroup.getName().equals(UserDao.read(adminId).getGroup().getName()));
                        }
                        //if a user without administrative privileges is moved to the ADMIN group, they will gain access to them
                        founded.setGroup(foundedGroup);
                        UserDao.update(founded);
                    }
                } catch (NumberFormatException e) {
                    LOGGER.info(e.getMessage());
                }
            }
        } else request.setAttribute("groupNotExists", true);

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