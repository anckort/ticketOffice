package servlets;

import controller.ItemController;
import entity.Item;
import entity.User;
import org.apache.log4j.Logger;
import service.ItemService;
import service.ItemServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/items")
public class ItemsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prsBtn = req.getParameter("btn");
        switch (prsBtn) {
            case "Add new item":
                resp.sendRedirect("/newItem");
                break;
            case "Delete selected":
                String[] ar = req.getParameterValues("id");
                ItemService itemService = new ItemServiceImp();
                try {
                    itemService.deleteSelectedItems(ar);
                } catch (ClassNotFoundException
                        | SQLException
                        | IllegalAccessException
                        | InstantiationException
                        | InvocationTargetException
                        | NullPointerException
                        | NoSuchMethodException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                }
                doGet(req,resp);
                break;
            case "To menu":
                resp.sendRedirect("/menu");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.sendRedirect("/index");
        }else{
            try {
                ItemService itemService = new ItemServiceImp();
                ArrayList <Item> list = itemService.getListOfItems();
                req.setAttribute("listOfItems", list);
                req.getRequestDispatcher("/WEB-INF/items.jsp").forward(req, resp);
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


    }

}

