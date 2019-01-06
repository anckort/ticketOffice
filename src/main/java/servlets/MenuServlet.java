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
//        User user = (User) req.getAttribute("user");
        switch (pressedUrl){
            case "Warehouse":
                req.getRequestDispatcher("WEB-INF/classes/warehouse.jsp").forward(req,resp);
                break;
            case "Items":
                req.getRequestDispatcher("/items").forward(req,resp);
                break;
        }

    }
}
