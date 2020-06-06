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

@WebServlet("/adminEditExercise")
public class AdminEditExerciseController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramTitle = request.getParameter("title");
        String paramDescription = request.getParameter("description");
        int exerciseId = (int) request.getSession().getAttribute("exerciseId");

        Map<String, String> fieldNames = Map.of("title", paramTitle, "description", paramDescription);
        boolean hasBlankFields = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        Map<String, Map<Integer, String>> capacitiesOfFields = Map.of("title", Map.of(256, paramTitle),
                "description", Map.of(65535, paramDescription));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfFields, request);

        if (!hasBlankFields && !hasCapacityExceededFields) {
            Exercise updatedExercise = new Exercise(exerciseId, paramTitle, paramDescription);
            ExerciseDao.update(updatedExercise);

            request.setAttribute("isUpdated", true);
            request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramTitle = request.getParameter("title");
        String paramDescription = request.getParameter("description");

        String paramExerciseId = request.getParameter("exerciseId");
        if (paramExerciseId == null || paramExerciseId.equals("")) {
            int exerciseId = (int) request.getSession().getAttribute("exerciseId");
            paramExerciseId = String.valueOf(exerciseId);
        }

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                Exercise founded = ExerciseDao.read(exerciseId);
                request.setAttribute("exercise", founded);
                if (founded == null) {
                    request.setAttribute("exerciseNotExists", true);
                } else if (paramTitle == null && paramDescription == null) {
                    request.getSession().setAttribute("exerciseId", founded.getId());
                    DataFiller.modelAttributesFiller(
                            Map.of("title", founded.getTitle(), "description", founded.getDescription()), request);
                } else
                    DataFiller.modelAttributesFiller(
                            Map.of("title", paramTitle, "description", paramDescription), request);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                .forward(request, response);
    }

}