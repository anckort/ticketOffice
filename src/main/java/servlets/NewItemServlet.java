package servlets;

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

@WebServlet (urlPatterns = "/newItem")
public class NewItemServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.sendRedirect("/index");
        }else{
            req.getRequestDispatcher("/WEB-INF/newItem.jsp").forward(req,resp);
        }

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
                    resp.sendRedirect("/items");
                } catch (ClassNotFoundException
                        | SQLException
                        | IllegalAccessException
                        | InstantiationException
                        | InvocationTargetException
                        | NullPointerException
                        | NoSuchMethodException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "Cancel":
                resp.sendRedirect("/items");
                break;
        }

    }
}
