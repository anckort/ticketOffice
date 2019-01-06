package controller;

import connectors.ConnectionToDB;
import entity.Item;

import javax.xml.transform.Result;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WarehouseController {
    public ArrayList<Item> getItemsOnWarehouse() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Item> list = new ArrayList <Item>();
        ConnectionToDB connection = new ConnectionToDB();
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from warehouse;");
        while (rs.next()){

        }
        return list;
    }
}
