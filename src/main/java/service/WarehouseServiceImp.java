package service;

import connectors.ConnectionToDB;
import entity.Item;
import entity.WarehouseItem;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * class of warehouse service
 * @author  Stas Bondarchuk
 */
public class WarehouseServiceImp implements WarehouseService {
    private static final Logger LOGGER = Logger.getLogger(WarehouseServiceImp.class.getName());

    /**
     * function of getting list of items from warehouse
     */
    @Override
    public ArrayList<WarehouseItem> getListOfWarehousItems() throws IllegalAccessException {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        PreparedStatement statement = null;
        try {
            statement = connection.getConnection().prepareStatement("select  i.name, SUM(w.count), i.iditems, i.code from warehouse w left join items i on w.item=i.iditems group by i.iditems,i.name,i.code;");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        ArrayList list = new ArrayList <>();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
            while (rs.next()){
                list.add(new WarehouseItem(rs.getInt(2),new Item(rs.getInt(3),rs.getString(1),rs.getString(4))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        connection.closeConnection();
        return list;
    }

    /**
     * function of checking count of items in warehouse
     */
    @Override
    public boolean checkCountOfItems(int itemId, int count) {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        PreparedStatement statement = null;
        try {
            statement = connection.getConnection().prepareStatement("select  w.item, SUM(w.count) from warehouse w where item = ? group by w.item;");
            statement.setInt(1,count);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                if (rs.getInt(2)>=count){
                    connection.closeConnection();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        connection.closeConnection();
        return false;
    }

    /**
     * function of adding items to warehouse list
     */
    @Override
    public boolean addItemToWarehouse(int itemId, int count) throws IllegalAccessException {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        PreparedStatement statement = null;
        try {
            statement = connection.getConnection().prepareStatement("INSERT into warehouse(item,count) VALUES (?,?);" );
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        try {
            statement.setInt(1,itemId);
            statement.setInt(2,count);
            int rs = statement.executeUpdate();
            if(rs == 1 ){
                LOGGER.info("added new item to warehouse");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.error("error of adding new item to warehouse");
        return false;
    }
}
