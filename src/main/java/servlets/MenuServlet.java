package servlets;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pressedUrl = (String) req.getParameter("ref");
        switch (pressedUrl){
            case "Warehouse":
                resp.sendRedirect("/warehouse");
                break;
            case "Items":
                resp.sendRedirect("/items");
                break;
            case "Registration":
                resp.sendRedirect("/users");
                break;
            case "Cash desk":
                resp.sendRedirect("/cashDesk");
                break;
            case"Cancellation":
                resp.sendRedirect("/cancellation");
                break;
            case"X Report":
                resp.sendRedirect("/xReport");
                break;
            case"Log out":
                req.getSession().setAttribute("user",null);
                resp.sendRedirect("/index");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/index").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/menu.jsp").forward(req,resp);
        }

    }
}
