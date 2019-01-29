package servlets;

import entity.WarehouseItem;
import org.apache.log4j.Logger;
import service.WarehouseService;
import service.WarehouseServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/warehouse")
public class WarehouseServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            WarehouseService warehouseService = new WarehouseServiceImp();
            ArrayList <WarehouseItem> list = null;
            list = warehouseService.getListOfWarehousItems();
            req.setAttribute("listOfItems", list);
            req.getRequestDispatcher("/WEB-INF/warehouse.jsp").forward(req, resp);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | InstantiationException
                | NullPointerException
                | SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String btn = req.getParameter("btn");
        switch (btn) {
            case "To menu":
                resp.sendRedirect("/menu");
                break;
            case "Add item to warehouse":
                resp.sendRedirect("/addToWarehouse");
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/warehouse.jsp").forward(req, resp);
        }

    }
}
