package servlets;

import entity.CashDeskItem;
import entity.Item;
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
        req.setAttribute("listOfItems",listOfItems);
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
                    req.getRequestDispatcher("/WEB-INF/cashDesk.jsp").forward(req,resp);
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
                String count = req.getParameter("count");
                if(count == null){
                    break;
                }
                try {
                    Item item = itemService.getItemByCodeOrName(fieldForSearch);
                    CashDeskItem cashDeskItem = new CashDeskItem(item,Integer.parseInt(count));
                    listOfItems.add(cashDeskItem);
                    req.setAttribute("listOfItems",listOfItems);
                    req.getRequestDispatcher("/WEB-INF/cashDesk.jsp").forward(req,resp);
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
                    cashDeskService.AddSale(listOfItems);
                    req.getRequestDispatcher("/WEB-INF/cashDesk.jsp").forward(req,resp);
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
                req.getRequestDispatcher("/WEB-INF/menu.jsp").forward(req,resp);
                break;
            case "Cancel":
                listOfItems = (ArrayList <CashDeskItem>) req.getSession().getAttribute("listOfItems");
                listOfItems.clear();
                req.getSession().setAttribute("listOfItems",listOfItems);
                req.getRequestDispatcher("/WEB-INF/cashDesk.jsp").forward(req,resp);
        }

    }
}
