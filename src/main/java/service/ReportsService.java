package service;

import entity.CashDeskItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface ReportsService {
    ArrayList getSaledItemsXReport(Date date) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
