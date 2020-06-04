package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.GroupDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Exercise;
import pl.codeschool.model.Group;
import pl.codeschool.model.User;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/adminEditExercise")
public class AdminEditExercise extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String paramExerciseId = request.getParameter("exerciseId");

        Exercise exercise = (Exercise) request.getSession().getAttribute("exercise");
        int exerciseId = 0;
        if (exercise != null) {
            exerciseId = exercise.getId();
        }

        Map<String, String> fieldNames = Map.of("title", title, "description", description);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        Map<String,Map<Integer, String>> capacitiesOfFields = Map.of("title",Map.of(256,title), "description", Map.of(65535,description));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && !hasCapacityExceededFields) {
            Exercise updatedExercise = new Exercise(exerciseId, title, description);
            ExerciseDao.update(updatedExercise);

            request.setAttribute("isUpdated", true);
            request.setAttribute("exerciseId", paramExerciseId);

            request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                Exercise founded = ExerciseDao.read(exerciseId);
                if (founded != null) {
                    request.getSession().setAttribute("exercise", founded);
                    DataFiller.modelAttributesFiller(
                            Map.of("title", founded.getTitle(), "description", founded.getDescription()), request);
                }
                if (title != null && description != null) {
                    DataFiller.modelAttributesFiller(
                            Map.of("title", title, "description", description), request);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                .forward(request, response);
    }
}