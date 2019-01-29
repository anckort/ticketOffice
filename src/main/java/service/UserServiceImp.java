package service;

import connectors.ConnectionToDB;
import entity.User;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImp implements UserService{
    private static final Logger LOGGER = Logger.getLogger(UserServiceImp.class.getName());

    public User checkLogIn(String username, String password) {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
            PreparedStatement statement = connection.getConnection().prepareStatement("select * from users where name=? and password=?;");
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                User user = new User(rs.getString(2),rs.getString(4),rs.getInt(1));
                connection.closeConnection();
                return user;
            }
            connection.closeConnection();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean addNewUser(String username, String password, String role) throws IllegalAccessException {
        ConnectionToDB connection = null;
        try {
            connection = new ConnectionToDB();
            PreparedStatement statement = connection.getConnection().prepareStatement("INSERT INTO users(name,password, role) VALUES (?,?,?);");
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,role);
            int result = statement.executeUpdate();

            if (result == 1){
                connection.closeConnection();
                LOGGER.info("added new user");
                connection.closeConnection();
                return true;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.error("error of adding new user");
        return false;
    }

    @Override
    public ArrayList<User> getListOfUsers() {
        ArrayList<User> list = new ArrayList <>();
        try {
            ConnectionToDB connection = new ConnectionToDB();
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT u.name AS name, u.role AS role, u.idusers AS id FROM users u");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(new User(rs.getString("name"),rs.getString("role"),rs.getInt("id")));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
