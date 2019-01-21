package servlets;

import controller.UserController;
import entity.User;
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

@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//      resp.getWriter().write("yeas");
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImp();
        User user = null;
        try {
            user = userService.checkLogIn(username,password);
            if(user != null){
                req.setAttribute("user",user);
                req.getRequestDispatcher("/WEB-INF/menu.jsp").include(req,resp);
            }
        } catch (ClassNotFoundException
                | SQLException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | InstantiationException e) {
            e.printStackTrace();
        }


    }
}

