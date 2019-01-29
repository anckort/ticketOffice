package servlets;

import entity.User;
import org.apache.log4j.Logger;
import service.ReportsService;
import service.ReportsServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/xReport")
public class XReportServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/index").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/xReport.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btn = req.getParameter("btn");
        switch (btn){
            case "To menu":
                resp.sendRedirect("/menu");
                break;
            case "Generate":
                ReportsService reportsService = new ReportsServiceImp();
                String strDate = req.getParameter("date");
                if (strDate == null){
                    LOGGER.error("empty parameter 'data'");
                    req.getRequestDispatcher("/WEB-INF/xReport.jsp").forward(req,resp);
                    return;
                }
                ArrayList list = new ArrayList();
                try {
                    list = reportsService.getSaledItemsXReport(strDate);
                    req.getSession().setAttribute("listOfItems",list);
                    req.getRequestDispatcher("/WEB-INF/xReport.jsp").forward(req,resp);
                } catch (InstantiationException
                        | InvocationTargetException
                        | SQLException
                        | NoSuchMethodException
                        | ClassNotFoundException
                        | NullPointerException
                        | IllegalAccessException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
        }

    }
}
