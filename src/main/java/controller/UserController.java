package controller;

import connectors.ConnectionToDB;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
    User user;
    public boolean checkLogIn(String username, String password) throws ClassNotFoundException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from users where name="+username+"and password="+password);
        if (rs.next()){
//            User user = new User(rs.getString(2),rs.getString(3),rs.getInt(1));
            return true;
        }
        return false;
    }
}
