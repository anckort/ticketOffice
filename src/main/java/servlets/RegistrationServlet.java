package servlets;

import controller.UserController;
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
                        resp.getWriter().write("Succes!");
//                        resp.reset();
                    }
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException
                        | IllegalAccessException
                        | SQLException e) {
                    e.printStackTrace();
                }
        }

    }
}
