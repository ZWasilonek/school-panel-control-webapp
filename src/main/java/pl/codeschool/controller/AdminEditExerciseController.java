package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@WebServlet("/admin/edit/exercise")
public class AdminEditExerciseController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminEditExerciseController.class);

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

        Integer exerciseId = getExerciseId(request, paramExerciseId);

        if (exerciseId != null) {
            Exercise founded = ExerciseDao.read(exerciseId);

            if (founded == null) {
                request.setAttribute("exerciseNotExists", true);
            } else if (paramTitle == null && paramDescription == null) {
                request.getSession().setAttribute("exerciseId", founded.getId());
                DataFiller.modelAttributesFiller(
                        Map.of("title", founded.getTitle(), "description", founded.getDescription()), request);
            } else
                DataFiller.modelAttributesFiller(
                        Map.of("title", paramTitle, "description", paramDescription), request);

            request.setAttribute("exercise", founded);
        }
        request.getRequestDispatcher("/WEB-INF/admin-edit-exercise.jsp")
                .forward(request, response);
    }

    private Integer getExerciseId(HttpServletRequest request, String paramExerciseId) {
        Integer exerciseId = null;

        if (paramExerciseId == null || "".equals(paramExerciseId)) {
            exerciseId = (Integer) request.getSession().getAttribute("exerciseId");
        } else {
            try {
                exerciseId = Integer.parseInt(paramExerciseId);
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return exerciseId;
    }

}