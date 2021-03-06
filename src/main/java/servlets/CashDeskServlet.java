package servlets;

import entity.CashDeskItem;
import entity.Item;
import entity.User;
import org.apache.log4j.Logger;
import service.CashDeskService;
import service.CashDeskServiceImp;
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

@WebServlet (urlPatterns = "/cashDesk")
public class CashDeskServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CashDeskServlet.class.getName());

    ArrayList<CashDeskItem> listOfItems = new ArrayList <CashDeskItem>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.sendRedirect("/index");
        }else{
            req.getRequestDispatcher("/WEB-INF/cashDesk.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String btn  = req.getParameter("btn");
        switch (btn){
            case "Find":

                try {
                    ItemService itemService = new ItemServiceImp();
                    String fieldForSearch = req.getParameter("fieldForSearch");
                    Item item = itemService.getItemByCodeOrName(fieldForSearch);
                    req.setAttribute("item",item);
                    req.getRequestDispatcher("WEB-INF/cashDesk.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | IllegalAccessException
                        | InstantiationException
                        | NullPointerException
                        | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "Add":

                ItemService itemService = new ItemServiceImp();
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                if (listOfItems == null){
                    listOfItems = new ArrayList <CashDeskItem>();
                }
                String fieldForSearch = req.getParameter("fieldForSearch");
                try {
                    int count = Integer.parseInt(req.getParameter("count"));
                    Item item = itemService.getItemByCodeOrName(fieldForSearch);
                    CashDeskItem cashDeskItem = new CashDeskItem(item,count,0,null,0);
                    listOfItems.add(cashDeskItem);
                    req.getSession().setAttribute("listOfItems",listOfItems);
                    req.getRequestDispatcher("WEB-INF/cashDesk.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | InvocationTargetException
                        | IllegalAccessException
                        | NullPointerException
                        | NumberFormatException
                        | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "Sale":
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                CashDeskService cashDeskService = new CashDeskServiceImp();
                try {
                    User user = (User) req.getSession().getAttribute("user");
                    cashDeskService.AddSale(listOfItems,user);
                    listOfItems.clear();
                    req.getSession().setAttribute("listOfItems",listOfItems);
                    req.getRequestDispatcher("WEB-INF/cashDesk.jsp").forward(req,resp);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InstantiationException
                        | InvocationTargetException
                        | IllegalAccessException
                        | NullPointerException
                        | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
                }
                break;
            case "To menu":
                resp.sendRedirect("/menu");
                break;
            case "Cancel":
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                if (listOfItems != null) {
                    listOfItems.clear();
                }
                req.getSession().setAttribute("listOfItems",listOfItems);
                req.getRequestDispatcher("WEB-INF/cashDesk.jsp").forward(req,resp);
        }

    }
}
