package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@WebServlet("/solution/edit")
public class SolutionEditController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolutionEditController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramDescription = request.getParameter("description");
        int exerciseId = (int) request.getSession().getAttribute("exerciseId");
        int solutionId = (int) request.getSession().getAttribute("solutionId");
        Integer userId = getUserId(request);

        Map<String, String> fieldNames = Map.of("description", paramDescription);
        boolean hasBlankField = BlankValidation.hasBlankErrorsAttributes(fieldNames, request);

        Map<String,Map<Integer, String>> capacitiesOfField = Map.of("description", Map.of(65535,paramDescription));
        boolean hasCapacityExceededFields = CapacityValidation.hasCapacityErrorAttributes(capacitiesOfField, request);

        if (!hasBlankField && !hasCapacityExceededFields) {
            Exercise foundedExercise = ExerciseDao.read(exerciseId);
            User foundedUser = UserDao.read(userId);
            Solution foundedSolution = SolutionDao.read(solutionId);
            if (foundedExercise != null && foundedUser != null && foundedSolution != null) {
                Solution updatedSolution = new Solution(solutionId, foundedUser, foundedExercise, paramDescription);
                SolutionDao.update(updatedSolution);
                request.setAttribute("isUpdated", true);
            }
            request.getRequestDispatcher("/WEB-INF/solution-edit.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramDescription = request.getParameter("description");
        String paramExerciseId = request.getParameter("exerciseId");
        String paramSolutionId = request.getParameter("solutionId");
        Integer userId = getUserId(request);
        Integer exerciseId = getId(request, paramExerciseId);
        Integer solutionId = getId(request,paramSolutionId);

        if (exerciseId != null && userId != null && solutionId != null) {
            Exercise foundedExercise = ExerciseDao.read(exerciseId);
            User foundedUser = UserDao.read(userId);
            Solution foundedSolution = SolutionDao.read(solutionId);

            if (foundedExercise != null && foundedUser != null && foundedSolution != null) {
                if (paramDescription != null) {
                    DataFiller.modelAttributesFiller(
                            Map.of("description", paramDescription), request);
                } else {
                    DataFiller.modelAttributesFiller(
                            Map.of("description", foundedSolution.getDescription()), request);
                }
                request.getSession().setAttribute("exerciseId", foundedExercise.getId());
                request.getSession().setAttribute("solutionId", foundedSolution.getId());
                request.setAttribute("exercise", foundedExercise);
            } else if (foundedExercise == null) {
                request.setAttribute("exerciseNotExists", true);
            } else if (foundedSolution == null)
                request.setAttribute("solutionNotExists", true);
        }
        request.getRequestDispatcher("/WEB-INF/solution-edit.jsp").forward(request,response);
    }

    private Integer getUserId(HttpServletRequest request) {
        Integer userId;
        userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            userId = (Integer) request.getSession().getAttribute("adminId");
        }
        return userId;
    }

    private Integer getId(HttpServletRequest request, String paramId) {
        Integer id = null;

        if (paramId == null || "".equals(paramId)) {
            id = (Integer) request.getSession().getAttribute(paramId);
        } else {
            try {
                id = Integer.parseInt(paramId);
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return id;
    }

}