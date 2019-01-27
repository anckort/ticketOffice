package servlets;

import entity.CashDeskItem;
import entity.Item;
import entity.User;
import service.CashDeskService;
import service.CashDeskServiceImp;
import service.ItemService;
import service.ItemServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet (urlPatterns = "/cashDesk")
public class CashDeskServlet extends HttpServlet {
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
                        | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Add":

                ItemService itemService = new ItemServiceImp();
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                if (listOfItems == null){
                    listOfItems = new ArrayList <CashDeskItem>();
                }
                String fieldForSearch = req.getParameter("fieldForSearch");
                int count = Integer.parseInt(req.getParameter("count"));

                try {
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
                        | SQLException e) {
                    e.printStackTrace();
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
                        | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "To menu":
                resp.sendRedirect("/menu");
                break;
            case "Cancel":
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                listOfItems.clear();
                req.getSession().setAttribute("listOfItems",listOfItems);
                req.getRequestDispatcher("WEB-INF/cashDesk.jsp").forward(req,resp);
        }

    }
}
