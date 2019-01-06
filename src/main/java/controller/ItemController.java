package controller;

import connectors.ConnectionToDB;
import entity.Item;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemController {
    Item item;
    public ArrayList<Item> getListOfItems() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Item> list = new ArrayList<Item>();
        ConnectionToDB connection = new ConnectionToDB();
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from items;");
        while (rs.next()){
            list.add(new Item(rs.getInt("iditems"),rs.getString("name"),rs.getString("code")));
        }
        return list;
    }
}
