package pl.codeschool.controller;

import pl.codeschool.dao.SolutionDao;
import pl.codeschool.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infoSolution")
public class InfoSolutionController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String paramSolutionId = request.getParameter("solutionId");

        if (paramSolutionId != null && !"".equals(paramSolutionId)) {
            try {
                int solutionId = Integer.parseInt(paramSolutionId);
                Solution solution = SolutionDao.read(solutionId);

                if (solution != null) {
                    request.setAttribute("solution", solution);
                } else request.setAttribute("solutionNotExists", true);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/solution-info.jsp")
                .forward(request, response);
    }

}