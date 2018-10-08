package main.java.Lab_2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")

/*
 * ControllerServlet, определяющий тип запроса, и, в зависимости от того, содержит ли запрос информацию о координатах точки и радиусе,
 * делегирующий его обработку одному из перечисленных ниже компонентов.
 * Все запросы внутри приложения должны передаваться этому сервлету (по методу GET или POST в зависимости от варианта задания),
 * остальные сервлеты с веб-страниц напрямую вызываться не должны
 */

public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("X");
        String y = request.getParameter("Y");
        String r = request.getParameter("R");
        if ((x == null) || (y == null) || (r == null)) {
            request.getRequestDispatcher("/lab2.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/areaChecker").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
