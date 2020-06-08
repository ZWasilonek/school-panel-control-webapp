package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.dao.UserDao;
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

@WebServlet("/addSolution")
public class AddSolutionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramDescription = request.getParameter("description");
        int exerciseId = (int) request.getSession().getAttribute("exerciseId");
        int userId = (int) request.getSession().getAttribute("userId");

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
                request.setAttribute("exerciseCreated", true);
            }
            request.getRequestDispatcher("/WEB-INF/admin-add-exercise.jsp")
                    .forward(request, response);
        } else doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        request.getRequestDispatcher("/WEB-INF/solution-add.jsp").forward(request,response);

    }
}
