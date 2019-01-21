package servlets;

import controller.ItemController;
import entity.Item;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prsBtn = req.getParameter("btn");
        switch (prsBtn) {
            case "Add new item":
                req.getRequestDispatcher("//WEB-INF/newItem.jsp").forward(req, resp);
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
                        | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                doPost(req,resp);
                break;
            case "To menu":
                req.getRequestDispatcher("//WEB-INF/menu.jsp").forward(req, resp);
                break;
        }

    }

}

