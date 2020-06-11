package pl.codeschool.controller;

import pl.codeschool.dao.SolutionDao;
import pl.codeschool.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution/delete")
public class SolutionDeleteController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramSolutionId = request.getParameter("solutionId");

        int exerciseId = 0;

        if (paramSolutionId != null && !"".equals(paramSolutionId)) {
            try {
                int solutionId = Integer.parseInt(paramSolutionId);
                Solution founded = SolutionDao.read(solutionId);
                if (founded != null) {
                    SolutionDao.delete(solutionId);
                    exerciseId = founded.getExercise().getId();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/exercise/solutions/info?exerciseId=" + exerciseId).forward(request, response);
    }

}