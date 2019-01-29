package service;

import entity.CashDeskItem;
import entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CashDeskService {
void AddSale(ArrayList<CashDeskItem> listOfItems, User user) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
ArrayList<CashDeskItem> getSalesByTicketID(int ticketID) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
void cancelSale(String[] list) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
