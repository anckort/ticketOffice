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
import java.util.Map;

public class ReportsServiceImp implements ReportsService {
    @Override
    public ArrayList getSaledItemsXReport(Date date) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();

        ArrayList list = new ArrayList <>();

        final LocalDateTime endOfDay       = LocalDateTime.of(LocalDate.from(date.toInstant()), LocalTime.MAX);
        final Date          endOfDayAsDate = Date.from(endOfDay.toInstant(ZoneOffset.UTC));

        final LocalDateTime startOfDay       = LocalDateTime.of(LocalDate.from(date.toInstant()), LocalTime.MIN);
        final Date          startOfDayAsDate = Date.from(startOfDay.toInstant(ZoneOffset.UTC));

        PreparedStatement statement = connection.getConnection().prepareStatement("" +
                "SELECT " +
                "i.name AS itemName, " +
                "t.idtickets AS idtickets," +
                "c.count AS itemCount," +
                "u.name AS userName," +
                "c.date AS saleDate" +
                "FROM cashdesk c " +
                "LEFT JOIN tickets t ON c.ticket = t.idtickets " +
                "LEFT JOIN items i on c.item = i.iditems " +
                "LEFT JOIN users u on t.user = u.idusers" +
                "WHERE t.date BETWEEN ? AND ?;");
        Timestamp begOfDayT = new Timestamp(startOfDayAsDate.getTime());
        Timestamp endOfDayT = new Timestamp(endOfDayAsDate.getTime());
        statement.setTimestamp(1,begOfDayT);
        statement.setTimestamp(2,endOfDayT);

        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            HashMap<String,Object> reportStr = new HashMap <>();
            reportStr.put("item",new Item(0,rs.getString("itemName"),""));
            reportStr.put("ticket", "Ticket #"+rs.getString("idtickets"));
            reportStr.put("saleDate",new Date(rs.getTimestamp("saleDate").getTime()));
            reportStr.put("user",new User(rs.getString("userName"),"",0));
            reportStr.put("count", rs.getString("itemCount"));
            list.add(reportStr);

        }

        return list;

    }
}
