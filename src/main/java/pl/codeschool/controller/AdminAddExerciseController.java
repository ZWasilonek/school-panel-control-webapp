package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Exercise;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/add/exercise")
public class AdminAddExerciseController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Map<String, String> fieldNames = Map.of("title", title, "description", description);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        Map<String,Map<Integer, String>> capacitiesOfFields = Map.of("title",Map.of(256,title), "description", Map.of(65535,description));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && !hasCapacityExceededFields) {
            Exercise newExercise = new Exercise(title, description);
            ExerciseDao.create(newExercise);
            request.setAttribute("exerciseCreated", true);
            request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title != null && description != null) {
            Map<String, String> fieldNames = Map.of("title", title, "description", description);
            DataFiller.modelAttributesFiller(fieldNames, request);
        }

        request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
            .forward(request,response);
    }

}
