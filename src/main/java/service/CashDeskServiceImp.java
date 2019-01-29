package service;

import connectors.ConnectionToDB;
import entity.CashDeskItem;
import entity.Item;
import entity.User;
import org.apache.log4j.Logger;


import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class CashDeskServiceImp implements CashDeskService {
    private static final Logger LOGGER = Logger.getLogger(CashDeskServiceImp.class.getName());

    @Override
    public void AddSale(ArrayList<CashDeskItem> listOfItems, User user) {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        for (CashDeskItem item:listOfItems){
            try {
                connection.getConnection().setAutoCommit(false);
                Calendar cal = Calendar.getInstance();
                cal.setTime(new java.util.Date());
                cal.set(Calendar.MILLISECOND, 0);
                java.sql.Timestamp date = new java.sql.Timestamp(cal.getTimeInMillis());

                PreparedStatement statementTicket = connection.getConnection().prepareStatement("INSERT INTO tickets(user,date) VALUES (?,?);");
                statementTicket.setInt(1,user.getId());
                statementTicket.setTimestamp(2, date);
                statementTicket.executeUpdate();

                PreparedStatement statementCashDesk = connection.getConnection().prepareStatement("INSERT INTO cashdesk(item, count, date) VALUES (?,?,?);");
                statementCashDesk.setInt(1, item.getItem().getId());
                statementCashDesk.setInt(2, item.getCount());
                statementCashDesk.setTimestamp(3, date);
                statementCashDesk.executeUpdate();
                LOGGER.info("added new sale in to cash desk");

                PreparedStatement statementWarehouse = connection.getConnection().prepareStatement("INSERT  INTO warehouse(count, item) VALUES (?,?);");
                statementWarehouse.setInt(1, item.getCount() * -1);
                statementWarehouse.setInt(2, item.getItem().getId());
                statementWarehouse.executeUpdate();
                LOGGER.info("goods "+item.getItem().getName()+" are deducted from stock");


                connection.getConnection().commit();

                statementTicket = connection.getConnection().prepareStatement("SELECT t.idtickets FROM tickets t WHERE t.user = ? AND t.date = ?;");
                statementTicket.setInt(1,user.getId());
                statementTicket.setTimestamp(2, date);
                int ticketKey;
                ResultSet rs = statementTicket.executeQuery();
                if (rs.next()){
                     ticketKey = rs.getInt(1);
                }else{
                    connection.getConnection().rollback();
                    return;
                }

                PreparedStatement statementUpdateCD = connection.getConnection().prepareStatement("UPDATE cashdesk SET cashdesk.ticket = ? WHERE cashdesk.item = ? AND cashdesk.count = ? AND cashdesk.date = ?;");
                statementUpdateCD.setInt(1,ticketKey);
                statementUpdateCD.setInt(2,item.getItem().getId());
                statementUpdateCD.setInt(3,item.getCount());
                statementUpdateCD.setTimestamp(4, date);
                statementUpdateCD.executeUpdate();
                connection.getConnection().commit();
                LOGGER.info("added new sale ticketID="+String.valueOf(ticketKey));
            }catch (SQLException e){
                e.printStackTrace();
                try {
                    connection.getConnection().rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    LOGGER.error(e1.getMessage());
                }
                LOGGER.error(e.getMessage());
            }
        }
    }

    public ArrayList<CashDeskItem> getSalesByTicketID(int ticketID) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<CashDeskItem> list = new ArrayList <>();

        ConnectionToDB connection = new ConnectionToDB();
        PreparedStatement statement;
        if (ticketID == 0) {
            statement = connection.getConnection().prepareStatement("SELECT " +
                    "c.ticket AS ticket," +
                    "c.count AS count," +
                    "c.date AS date," +
                    "i.name AS name," +
                    "c.idcashDesk AS ID " +
                    "FROM cashdesk c LEFT JOIN items i on c.item = i.iditems " +
                    "WHERE c.canceled=0;");
        }else{
            statement = connection.getConnection().prepareStatement("SELECT " +
                    "c.ticket AS ticket," +
                    "c.count AS count," +
                    "c.date AS date," +
                    "i.name AS name," +
                    "c.idcashDesk AS ID " +
                    "FROM cashdesk c LEFT JOIN items i on c.item = i.iditems " +
                    "WHERE c.ticket=? " +
                    "AND c.canceled=0;");
            statement.setInt(1,ticketID);
        }

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            Item item = new Item(0,rs.getString("name"),"");
            list.add(new CashDeskItem(item,
                    rs.getInt("count"),
                    rs.getInt("ticket"),
                    rs.getDate("date"),
                    rs.getInt("ID")));
        }

        return list;
    }

    @Override
    public void cancelSale(String[] list) {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        String arByStr = (String) connection.prepareArrayForQuery(Arrays.asList(list));
        PreparedStatement statement = null;
        try {
            statement = connection.getConnection().prepareStatement("UPDATE cashdesk SET cashdesk.canceled = 1 WHERE cashdesk.idcashDesk IN ("+arByStr+");");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        try {
            statement.executeUpdate();
            connection.closeConnection();
            LOGGER.info("canceled sale");
        }catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }


    }


}
