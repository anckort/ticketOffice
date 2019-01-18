package servlets;

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

@WebServlet (urlPatterns = "/newItem")
public class NewItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prsBtn = req.getParameter("btn");
        switch (prsBtn){
            case "Add":
                try {
                    ItemService itemService = new ItemServiceImp();
                    String name = req.getParameter("name");
                    String code = req.getParameter("code");
                    itemService.addNewItem(name,code);
                    req.getRequestDispatcher("/items").forward(req,resp);
                } catch (ClassNotFoundException
                        | SQLException
                        | IllegalAccessException
                        | InstantiationException
                        | InvocationTargetException
                        | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                break;
            case "Cancel":
                req.getRequestDispatcher("/items").forward(req,resp);
                break;
        }

    }
}