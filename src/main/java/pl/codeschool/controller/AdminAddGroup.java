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

@WebServlet("/adminAddGroup")
public class AdminAddGroup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String groupName = request.getParameter("groupName");

        Map<String, String> fieldNames = Map.of("groupName", groupName);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);
        boolean isUniqueGroupName = checkUniqueGroupName(groupName, request);

        Map<String,Map<Integer, String>> capacitiesOfFields = Map.of("groupName",Map.of(256,groupName));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && isUniqueGroupName && !hasCapacityExceededFields) {
            Group newGroup = new Group(groupName);
            GroupDao.create(newGroup);

            request.setAttribute("groupCreated", true);
            request.getRequestDispatcher("/WEB-INF/admin-add-group.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String groupName = request.getParameter("groupName");
        if (groupName != null) {
            DataFiller.modelAttributesFiller(Map.of("groupName", groupName), request);
        }

        request.getRequestDispatcher("/WEB-INF/admin-add-group.jsp")
                .forward(request, response);
    }

    private boolean checkUniqueGroupName(String groupName, HttpServletRequest request) {
        final String NOT_UNIQUE = "a group with this name already exists";
        if (groupName != null && !"".equals(groupName)) {
            Group founded = GroupDao.readByName(groupName);
            if (founded != null) {
                request.setAttribute("notUniqueGroupName", NOT_UNIQUE);
                return false;
            }
        }
        return true;
    }

}