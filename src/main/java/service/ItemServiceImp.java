package service;

import connectors.ConnectionToDB;
import entity.Item;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemServiceImp implements ItemService {
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

    @Override
    public boolean addNewItem(String name, String code) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement = connection.getConnection().prepareStatement("INSERT INTO items(name,code) VALUES (?,?)");
        statement.setString(1,name);
        statement.setString(2,code);
        int rs = statement.executeUpdate();

        if (rs==1){
            connection.closeConnection();
            return true;
        }
        connection.closeConnection();
        return false;
    }

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

}
