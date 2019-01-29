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
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImp();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String pressedBtn = req.getParameter("btn");
        switch (pressedBtn){
            case "Save":
                try {
                    if (userService.addNewUser(username,password,role)){
                        resp.sendRedirect("/users");
                    }
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException
                        | IllegalAccessException
                        | NullPointerException
                        | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            resp.sendRedirect("/index");
        }else{
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req,resp);
        }

    }
}
