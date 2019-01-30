package service;

import connectors.ConnectionToDB;
import entity.Item;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * class of service item
 * @author  Stas Bondarchuk
 */
public class ItemServiceImp implements ItemService {
    private static final Logger LOGGER = Logger.getLogger(ItemServiceImp.class.getName());
    /**
     * function of getting list of items
     */
    @Override
    public ArrayList<Item> getListOfItems() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Item> list = new ArrayList<Item>();
        ConnectionToDB connection = new ConnectionToDB();
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from items;");

        while (rs.next()){
            list.add(new Item(rs.getInt("iditems"),rs.getString("name"),rs.getString("code")));
        }
        connection.closeConnection();
        return list;
    }

    /**
     * function of adding new items to list
     */
    @Override
    public boolean addNewItem(String name, String code) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement = connection.getConnection().prepareStatement("INSERT INTO items(name,code) VALUES (?,?)");
        statement.setString(1,name);
        statement.setString(2,code);
        int rs = statement.executeUpdate();
        LOGGER.info("added new item "+name);
        if (rs==1){
            connection.closeConnection();
            return true;
        }
        connection.closeConnection();
        return false;
    }

    /**
     * function of deletion new items from list
     */
    @Override
    public boolean deleteSelectedItems(String[] ar) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        String arByStr = connection.prepareArrayForQuery(Arrays.asList(ar));
        PreparedStatement statement = connection.getConnection().prepareStatement("DELETE FROM items WHERE iditems IN ("+arByStr+");");

        int rs = statement.executeUpdate();

        if (rs==1){
            connection.closeConnection();
            return true;
        }
        connection.closeConnection();
        return false;
    }

    /**
     * function of getting items from list by code or name
     */
    @Override
    public Item getItemByCodeOrName(String fieldForSearch) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement = connection.getConnection().prepareStatement("SELECT i.iditems, i.name, i.code FROM items i WHERE code = ? OR name = ?;");
        statement.setString(1,fieldForSearch);
        statement.setString(2,fieldForSearch);
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3));
            connection.closeConnection();
            return item;
        }
        return null;
    }

}
