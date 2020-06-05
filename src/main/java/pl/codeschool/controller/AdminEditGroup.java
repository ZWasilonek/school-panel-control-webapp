package pl.codeschool.controller;

import pl.codeschool.dao.GroupDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Group;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/adminEditGroup")
public class AdminEditGroup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupName = request.getParameter("groupName");
        int groupId = (int) request.getSession().getAttribute("groupId");

        Map<String, String> fieldNames = Map.of("groupName", paramGroupName);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);
        boolean isUniqueGroupName = checkUniqueGroupName(paramGroupName, groupId, request);

        Map<String,Map<Integer, String>> capacitiesOfFields = Map.of("groupName",Map.of(256,paramGroupName));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && isUniqueGroupName && !hasCapacityExceededFields) {
            Group updatedGroup = new Group(groupId, paramGroupName);
            GroupDao.update(updatedGroup);

            request.setAttribute("isUpdated", true);
            request.getRequestDispatcher("/WEB-INF/admin-edit-group.jsp")
                    .forward(request, response);

        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramGroupName = request.getParameter("groupName");

        String paramGroupId = request.getParameter("groupId");
        if (paramGroupId == null || paramGroupId.equals("")) {
            int groupId = (int) request.getSession().getAttribute("exerciseId");
            paramGroupId = String.valueOf(groupId);
        }

        if (!"".equals(paramGroupId)) {
            try {
                int groupId = Integer.parseInt(paramGroupId);
                Group founded = GroupDao.read(groupId);
                request.setAttribute("group", founded);
                if (founded == null) {
                    request.setAttribute("groupNotExists", true);
                } else if (paramGroupName == null) {
                    request.getSession().setAttribute("groupId", groupId);
                    DataFiller.modelAttributesFiller(Map.of("groupName", founded.getName()), request);
                } else
                    DataFiller.modelAttributesFiller(Map.of("groupName", paramGroupName), request);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-group.jsp")
                .forward(request, response);
    }

    private boolean checkUniqueGroupName(String groupName, int groupId, HttpServletRequest request) {
        final String NOT_UNIQUE = "a group with this name already exists";
        if (groupName != null && !"".equals(groupName)) {
            Group foundedByName = GroupDao.readByName(groupName);
            if (foundedByName != null && foundedByName.getId() != groupId) {
                request.setAttribute("notUniqueGroupName", NOT_UNIQUE);
                return false;
            }
        }
        return true;
    }

}
