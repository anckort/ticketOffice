package servlets;

import entity.CashDeskItem;
import entity.User;
import service.CashDeskService;
import service.CashDeskServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet (urlPatterns = "/cancellation")
public class CancellationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/index").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/cancellation.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String btn = req.getParameter("btn");
        CashDeskService cashDeskService = new CashDeskServiceImp();
        switch (btn){
            case "Find":
                String ticket = req.getParameter("ticketNumber");

                int ticketID;
                if (ticket == null){
                    ticketID = 0;
                }else{
                    ticketID = Integer.parseInt(ticket);
                }

                try {
                    ArrayList<CashDeskItem> list = cashDeskService.getSalesByTicketID(ticketID);
                    req.getSession().setAttribute("listOfItems", list);
                    req.getRequestDispatcher("/WEB-INF/cancellation.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | InvocationTargetException
                        | SQLException
                        | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "Cancel":
                String[] listOfID = req.getParameterValues("id");
                try {
                    cashDeskService.cancelSale(listOfID);
                    ticket = req.getParameter("ticketNumber");

                    if (ticket == null){
                        ticketID = 0;
                    }else{
                        ticketID = Integer.parseInt(ticket);
                    }
                    ArrayList<CashDeskItem> list = cashDeskService.getSalesByTicketID(ticketID);
                    req.getSession().setAttribute("listOfItems", list);
                    req.getRequestDispatcher("/WEB-INF/cancellation.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | InvocationTargetException
                        | SQLException
                        | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "Cancel all":
                ArrayList<CashDeskItem> listOfItems = (ArrayList) req.getSession().getAttribute("listOfItems");
                ArrayList<String> list = new ArrayList <>();
                String str ="";
                boolean first = true;
                for (CashDeskItem item:listOfItems){
                    if (first){
                        str = String.valueOf(item.getID());
                    }else {
                        str = str + "," + String.valueOf(item.getID());
                    }
                }
                String[] ar = str.split(",");
                try {
                    cashDeskService.cancelSale(ar);
                    listOfItems.clear();
                    req.getRequestDispatcher("/WEB-INF/cancellation.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | IllegalAccessException
                        | SQLException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
