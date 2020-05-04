package pl.codeschool.controller;

import pl.codeschool.dao.ExerciseDao;
import pl.codeschool.dao.SolutionDao;
import pl.codeschool.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/infoSolutions")
public class AdminExerciseSolutions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String paramExerciseId = request.getParameter("exerciseId");
        int exerciseId;
        if (paramExerciseId != null && !"".equals(paramExerciseId)) {
            try {
                exerciseId = Integer.parseInt(paramExerciseId);
                List<Solution> solutionsByExerciseId = SolutionDao.findAllByExerciseId(exerciseId);
                if (solutionsByExerciseId != null) {
                    request.setAttribute("solutions", solutionsByExerciseId);
                }
                String exerciseTitle = Objects.requireNonNull(ExerciseDao.read(exerciseId)).getTitle();
                request.setAttribute("exerciseTitle", exerciseTitle);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/solutions-list.jsp")
                .forward(request, response);
    }
}
