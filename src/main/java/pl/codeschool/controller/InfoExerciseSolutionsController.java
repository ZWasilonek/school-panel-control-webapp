package pl.codeschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.model.Exercise;
import pl.codeschool.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exercise/solutions/info")
public class InfoExerciseSolutionsController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoExerciseSolutionsController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");

        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                int exerciseId = Integer.parseInt(paramExerciseId);
                Exercise foundedExercise = ExerciseDao.read(exerciseId);

                if (foundedExercise == null) {
                    request.setAttribute("exerciseNotExists", true);
                } else {
                    request.setAttribute("exercise", foundedExercise);
                }

                List<Solution> solutionsByExerciseId = SolutionDao.findAllByExerciseId(exerciseId);
                if (solutionsByExerciseId != null) {
                    request.setAttribute("solutions", solutionsByExerciseId);
                }
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
            }
        }
        request.getRequestDispatcher("/WEB-INF/solutions-list.jsp")
                .forward(request, response);
    }

}
