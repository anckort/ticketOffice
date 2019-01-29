package servlets;

import entity.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            try {
                resp.sendRedirect("/index");
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }else{
            UserService userService = new UserServiceImp();
            ArrayList<User> list = userService.getListOfUsers();
            req.setAttribute("listOfUsers",list);
            try {
                req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req,resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String btn = req.getParameter("btn");
        try {
        switch (btn){
            case "To menu":
                resp.sendRedirect("/menu");
                break;
            case "Add new user":
                resp.sendRedirect("/registration");
                break;
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
