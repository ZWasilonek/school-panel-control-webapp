package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.dao.UserDao;
import pl.codeschool.mapper.DataFiller;
import pl.codeschool.model.Exercise;
import pl.codeschool.model.Solution;
import pl.codeschool.model.User;
import pl.codeschool.validation.BlankValidation;
import pl.codeschool.validation.CapacityValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/add/solution")
public class SolutionAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramDescription = request.getParameter("description");
        int exerciseId = (int) request.getSession().getAttribute("exerciseId");

        Integer userId;
        userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            userId = (Integer) request.getSession().getAttribute("adminId");
        }

        Map<String, String> fieldNames = Map.of("description", paramDescription);
        boolean hasBlankField = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        Map<String,Map<Integer, String>> capacitiesOfField = Map.of("description", Map.of(65535,paramDescription));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfField, request);

        if (!hasBlankField && !hasCapacityExceededFields) {
            Exercise foundedExercise = ExerciseDao.read(exerciseId);
            User foundedUser = UserDao.read(userId);
            if (foundedExercise != null && foundedUser != null) {
                Solution newSolution = new Solution(foundedUser, foundedExercise, paramDescription);
                SolutionDao.create(newSolution);
                request.setAttribute("solutionCreated", true);
            }
            request.getRequestDispatcher("/WEB-INF/solution-add.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramDescription = request.getParameter("description");
        String paramExerciseId = request.getParameter("exerciseId");

        Integer userId;
        userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            userId = (Integer) request.getSession().getAttribute("adminId");
        }

        Integer exerciseId = geExerciseId(request, paramExerciseId);

        if (exerciseId != null && userId != null) {
            Exercise foundedExercise = ExerciseDao.read(exerciseId);
            User foundedUser = UserDao.read(userId);

            if (foundedExercise != null && foundedUser != null) {
                if (paramDescription != null) {
                    DataFiller.modelAttributesFiller(
                            Map.of("description", paramDescription), request);
                }
                request.getSession().setAttribute("exerciseId", foundedExercise.getId());
                request.setAttribute("exercise", foundedExercise);
            }
        }
        request.getRequestDispatcher("/WEB-INF/solution-add.jsp").forward(request,response);
    }

    private Integer geExerciseId(HttpServletRequest request, String paramExerciseId) {
        Integer exerciseId = null;

        if (paramExerciseId == null || "".equals(paramExerciseId)) {
            exerciseId = (Integer) request.getSession().getAttribute("exerciseId");
        } else {
            try {
                exerciseId = Integer.parseInt(paramExerciseId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return exerciseId;
    }

}