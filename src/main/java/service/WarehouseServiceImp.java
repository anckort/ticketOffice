package service;

import connectors.ConnectionToDB;
import entity.Item;
import entity.WarehouseItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseServiceImp implements WarehouseService {
    @Override
    public ArrayList<WarehouseItem> getListOfWarehousItems() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement = connection.getConnection().prepareStatement("select  i.name, SUM(w.count), i.iditems, i.code from warehouse w left join items i on w.item=i.iditems group by i.iditems,i.name,i.code;");
        ResultSet rs = statement.executeQuery();
        ArrayList list = new ArrayList <>();
        while (rs.next()){
            list.add(new WarehouseItem(rs.getInt(2),new Item(rs.getInt(3),rs.getString(1),rs.getString(4))));
        }
        connection.closeConnection();
        return list;
    }

    @Override
    public boolean chekCountOfItems(int itemId, int count) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement = connection.getConnection().prepareStatement("select  w.item, SUM(w.count) from warehouse w where item = ? group by w.item;");
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            if (rs.getInt(2)>=count){
                connection.closeConnection();
                return true;

            }
        }
        connection.closeConnection();
        return false;
    }
}
