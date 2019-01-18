package service;

import connectors.ConnectionToDB;
import entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServiceImp implements UserService{
    public User checkLogIn(String username, String password) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConnectionToDB connection = new ConnectionToDB();
//        Statement statement = connection.getConnection().createStatement();
//        ResultSet rs = statement.executeQuery();
        PreparedStatement statement = connection.getConnection().prepareStatement("select * from users where name=? and password=?;");
        statement.setString(1,username);
        statement.setString(2,password);
//        statement.setString(3,role);
        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            User user = new User(rs.getString(2),rs.getString(3),rs.getInt(1));
            connection.closeConnection();
            return user;
        }
        connection.closeConnection();
        return null;
    }

    @Override
    public Boolean addNewUser(String username, String password, String role) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionToDB connection = new ConnectionToDB();
//        Statement statement = connection.getConnection().createStatement();
        PreparedStatement statement = connection.getConnection().prepareStatement("INSERT INTO users(name,password, role) VALUES (?,?,?);");
        statement.setString(1,username);
        statement.setString(2,password);
        statement.setString(3,role);
        int result = statement.executeUpdate();

        if (result == 1){
            connection.closeConnection();
            return true;
        }
        connection.closeConnection();
        return false;
    }
}
