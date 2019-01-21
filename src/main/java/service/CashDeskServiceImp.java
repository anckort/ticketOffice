package service;

import connectors.ConnectionToDB;
import entity.CashDeskItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CashDeskServiceImp implements CashDeskService {

    @Override
    public void AddSale(ArrayList<CashDeskItem> listOfItems) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();

        for (CashDeskItem item:listOfItems){
            PreparedStatement statementCashDesk = connection.getConnection().prepareStatement("INSERT INTO cashdesk(item, count, date) VALUES (?,?,?);");
            statementCashDesk.setInt(1,item.getItem().getId());
            statementCashDesk.setInt(2,item.getCount());
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            statementCashDesk.setTimestamp(3, date);
            int rsCashDesk = statementCashDesk.executeUpdate();


            PreparedStatement statementWarehouse = connection.getConnection().prepareStatement("INSERT  INTO warehouse(count, item) VALUES (?,?);");
            statementWarehouse.setInt(1,item.getCount()*-1);
            statementWarehouse.setInt(2,item.getItem().getId());
            int rsWarehouse = statementWarehouse.executeUpdate();
        }
    }
}
