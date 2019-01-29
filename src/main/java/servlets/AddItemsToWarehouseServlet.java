package servlets;

import entity.Item;
import entity.User;
import org.apache.log4j.Logger;
import service.ItemService;
import service.ItemServiceImp;
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

@WebServlet (urlPatterns = "/addToWarehouse" )
public class AddItemsToWarehouseServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddItemsToWarehouseServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String btn = req.getParameter("btn");
        switch (btn){
            case "Find by code":
                ItemService itemService = new ItemServiceImp();
                String code = req.getParameter("codeOfItem");
                try {
                    Item item = itemService.getItemByCodeOrName(code);
                    req.setAttribute("item",item);
                    req.setAttribute("itemID", item.getId());
                    req.getRequestDispatcher("/WEB-INF/addItemToWarehouse.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException
                        | SQLException
                        | NullPointerException
                        | IllegalAccessException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "Add":
                WarehouseService warehouseService = new WarehouseServiceImp();
                Integer itemID = Integer.parseInt(req.getParameter("itemID"));
                Integer count = Integer.parseInt(req.getParameter("countOfItem"));
                try {
                    boolean rs = warehouseService.addItemToWarehouse(itemID.intValue(),count.intValue());
                    String errorStr = "";
                    if (rs){
                       resp.sendRedirect("/warehouse");
                    }
                    else{
                        errorStr.concat("Not founded such item, id= "+itemID);
                        LOGGER.error(errorStr);
                        req.getRequestDispatcher("WEB-INF/addItemToWarehouse.jsp").forward(req,resp);
                    }
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | InvocationTargetException
                        | NullPointerException
                        | IllegalAccessException
                        | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "Cancel":
                req.getRequestDispatcher("/warehouse").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.sendRedirect("/index");
        }else{
            req.getRequestDispatcher("WEB-INF/addItemToWarehouse.jsp").forward(req,resp);
        }

    }
}
