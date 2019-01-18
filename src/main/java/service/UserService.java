package service;

import entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface UserService {
     User checkLogIn(String username, String password) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
     Boolean addNewUser(String username, String password, String role) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
