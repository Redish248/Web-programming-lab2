package main.java.Lab_2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/areaChecker")

/*
 * AreaCheckServlet, осуществляющий проверку попадания точки в область на координатной плоскости и формирующий HTML-страницу с результатами проверки.
 * Должен обрабатывать все запросы, содержащие сведения о координатах точки и радиусе области.
 */
public class AreaCheckServlet extends HttpServlet{
    private ServletConfig config;
    private List<Point> list = null;
    private List<PointBean> beanList = null;

    @Override
    public void init (ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {}

    @Override
    public ServletConfig getServletConfig()
    {
        return config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/controller");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (list == null) {
            list = new ArrayList();
            config.getServletContext().setAttribute("list", list);
        }

        if (beanList == null) {
            beanList = new ArrayList();
        }
        double x = Double.parseDouble(request.getParameter("X"));
        double y;
        if (request.getParameter("Y").contains(",")) {
            y = Double.parseDouble(request.getParameter("Y").replace(",", "."));
        } else {
            y = Double.parseDouble(request.getParameter("Y"));
        }

        double r;
        if (request.getParameter("R").contains(",")) {
            r = Double.parseDouble(request.getParameter("R").replace(",", "."));
        } else {
            r = Double.parseDouble(request.getParameter("R"));
        }

        if ((r >= 4) || (r <= 1) || (y >= 5) || (y <= -5) || (Double.isNaN(y)) || (Double.isNaN(r))) {
            request.getServletContext().getRequestDispatcher("/lab2.jsp").forward(request, response);
        } else {
            Point point = new Point(Double.parseDouble(request.getParameter("X")), y, r);
            list.add(point);
            point.isInArea = checkArea(point.x, point.y, point.R);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html> <!DOCTYPE HTML> <html> <head> <meta charset='UTF-8'> <title>Points</title>\n" +
                    "            <link rel='shortcut icon' href='images/duck.ico'>\n" +
                    "            <link rel='stylesheet' type='text/css' href='styles/main.css'>\n" +
                    "          </head> <body style='background-image: url(images/cat.png)'> <center>");
            out.println("<br><div  class='newWindow' style='padding:20px 0px;'> <br> <table class='points'>\n" +
                    "             <caption style='font-weight: bold'>Results:</caption>\n" +
                    "            <tr class = 'points1'>  <th scope='col' class = 'points1'>X</th> <th scope='col' class = 'points1'>Y</th> \n" +
                    "            <th scope='col' class = 'points1'>R</th> <th scope='col' class = 'points1'>Check area</th> \n</tr> ");
            for (int i = 0; i < list.size(); i++) {
                out.println("<td>");
                out.println(list.get(i).x);
                out.println("</td>");

                out.println("<td>");
                out.println(list.get(i).y);
                out.println("</td>");

                out.println("<td>");
                out.println(list.get(i).R);
                out.println("</td>");

                if (list.get(i).isInArea) {
                    out.println("<td>");
                    out.println("&#9989");
                    out.println("</td>");
                } else {
                    out.println("<td>");
                    out.println("&#10060");
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</table> <br> </div> <br>");
            out.println("<a href='lab2.jsp'> <button class='submit'> Return </button> </a>");
            out.println("</center> </body> </html>");

            boolean hit = false;
            PointBean bean = new PointBean();
            PointList pointList = new PointList();
            if (point.isInArea) {
                hit = true;
            }
            bean.setX(x);
            bean.setY(y);
            bean.setR(r);
            bean.setHit(hit);
            beanList.add(bean);
            pointList.setList(beanList);
            request.getSession().setAttribute("BeanPoints", pointList);
        }

    }

    public class Point {
        public double x;
        public double y;
        public double R;
        public boolean isInArea;

        Point (double x, double y, double r){
            this.x = x;
            this.y = y;
            this.R = r;
        }
    }

    public static boolean checkArea(double x, double y, double r){
        if (((x >= 0) && (y <= 0) && (y >= -r)  && (x <= r)) ||
                        ((x <= 0) && (y >= 0) && ((x * x + y * y) <= (r * r))) ||
                ((x <= 0) && (y <= 0) && (y >= -(x+r)/2))) {
            return true;
        }
        return false;
    }
}
