package service;

import entity.CashDeskItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CashDeskService {
void AddSale(ArrayList<CashDeskItem> listOfItems) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
