package controller;

import connectors.ConnectionToDB;
import entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
    User user;
    public User checkLogIn(String username, String password) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConnectionToDB connection = new ConnectionToDB();
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from users where name="+"'"+username+"'"+" and password="+"'"+password+"'"+";");
        if (rs.next()){
            User user = new User(rs.getString(2),rs.getString(3),rs.getInt(1));
            return user;
        }
        return null;
    }
}
