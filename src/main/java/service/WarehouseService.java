package service;

import entity.WarehouseItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WarehouseService {
    ArrayList<WarehouseItem> getListOfWarehousItems() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
    boolean checkCountOfItems(int itemId, int count) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
    boolean addItemToWarehouse(int itemId, int count) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
