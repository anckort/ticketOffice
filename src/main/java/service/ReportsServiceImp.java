package service;

import connectors.ConnectionToDB;
import entity.CashDeskItem;
import entity.Item;
import entity.User;


import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ReportsServiceImp implements ReportsService {
    @Override
    public ArrayList getSaledItemsXReport(String date) {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        ArrayList list = new ArrayList <>();
        final LocalDateTime endOfDay       = LocalDateTime.of(LocalDate.parse(date), LocalTime.MAX);
        final Date          endOfDayAsDate = Date.from(endOfDay.toInstant(ZoneOffset.UTC));

        final LocalDateTime startOfDay       = LocalDateTime.of(LocalDate.parse(date), LocalTime.MIN);
        final Date          startOfDayAsDate = Date.from(startOfDay.toInstant(ZoneOffset.UTC));

        PreparedStatement statement = null;
        try {
            statement = connection.getConnection().prepareStatement("" +
                    "SELECT " +
                    "i.name AS itemName, " +
                    "t.idtickets AS idtickets, " +
                    "c.count AS itemCount," +
                    "u.name AS userName, " +
                    " c.date AS saleDate " +
                    "FROM cashdesk c " +
                    "LEFT JOIN tickets t ON c.ticket = t.idtickets " +
                    "LEFT JOIN items i on c.item = i.iditems " +
                    "LEFT JOIN users u on t.user = u.idusers " +
                    "WHERE t.date BETWEEN ? AND ?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Timestamp begOfDayT = new Timestamp(startOfDayAsDate.getTime());
        Timestamp endOfDayT = new Timestamp(endOfDayAsDate.getTime());
        try {
            statement.setTimestamp(1,begOfDayT);
            statement.setTimestamp(2,endOfDayT);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
            while (rs.next()){
                HashMap<String,Object> reportStr = new HashMap <>();
                reportStr.put("item",rs.getString("itemName"));
                reportStr.put("ticket", "#"+rs.getString("idtickets"));
                reportStr.put("saleDate", rs.getTimestamp("saleDate"));
                reportStr.put("user", rs.getString("userName"));
                reportStr.put("count", rs.getString("itemCount"));
                list.add(reportStr);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
}
