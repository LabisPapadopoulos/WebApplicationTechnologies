package gr.uoa.di.ted.std08169.std09158.dao;

import gr.uoa.di.ted.std08169.std09158.domain.Role;
import gr.uoa.di.ted.std08169.std09158.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Prospelaunei xrhstes apo tin vash
 */
public class UserDao {
    private Connection connection;

    public UserDao() throws SQLException, ClassNotFoundException {
        //fortwsh ths klashs tou driver
        Class.forName("com.mysql.jdbc.Driver");
        //sundesh me tin vash
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectManagement?user=root&password=root");
   }
    
   public boolean usernameExists(String username) throws SQLException {
       //dhmiourgeia eperwtishs
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `Users` WHERE `username` = '" + username + "';");
       //elenxei an uparxei h grammh     periexomeno ths 1hs sthlhs logo tou count(*)
       return (resultSet.next()) && (resultSet.getInt(1) == 1); //vrike mia grammh
   }
   
   public void addUser(User user) throws SQLException {
       connection.createStatement().executeUpdate("INSERT INTO `Users` "
               + "(`username`, `password`, `name`, `surname`, `email`, `role`) " +
               "VALUES ('" + user.getUsername() + "', '" + user.getPassword() +
               "', '" + user.getName() + "', '" + user.getSurname() + "', '" +
               user.getEmail() + "', '" + user.getRole() + "');");
   }
   
   /**
    * Epistrefei ton user an uparxei gia na apothikeutei sto p.x: SESSION
    * @param username to onoma tou xrhsth pou psaxnoume
    * @param password to password tou xrhsth pou psaxnoume
    * @return enan User 'h null an o xrhsths den vrethike
    * @throws SQLException an sumvei otidhpote me tin vash
    */
   public User getUser(String username, String password) throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `Users` "
               + "WHERE `username` = '" + username + "' and `password` = '" + password + "';");
       
       return resultSet.next() ? new User(resultSet.getString("username"), 
               resultSet.getString("password"), resultSet.getString("name"),
               resultSet.getString("surname"), resultSet.getString("email"), 
               Role.valueOf(resultSet.getString("role"))) : null;
   }
   
   public List<User> getUsers() throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `Users`"
               + "ORDER BY `username` ASC;");
       
       //xrhsh arrayList gia tin epistrofh twn xrhstwn
       List<User> users = new ArrayList<User>();
       while(resultSet.next())//oso uparxoun grammes sto resultSet - apotelesma
           //prosthhkh neou xrhsth sto arrayList
           users.add(new User(resultSet.getString("username"), 
               resultSet.getString("password"), resultSet.getString("name"),
               resultSet.getString("surname"), resultSet.getString("email"), 
               Role.valueOf(resultSet.getString("role"))));
       
       return users; 
   }
   
   public void setRole(String username, Role role) throws SQLException {
       connection.createStatement().executeUpdate("UPDATE `Users` SET `role` = '" + role + "' WHERE `username` = '" + username + "';");
   }
   
   public List<String> getManagers()throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `username` FROM `Users` "
               + " WHERE `role` = 'MANAGER' ORDER BY `username` ASC;");
       
       //xrhsh arrayList gia tin epistrofh twn xrhstwn
       List<String> managers = new ArrayList<String>();
       while(resultSet.next())//oso uparxoun grammes sto resultSet - apotelesma
           //prosthhkh neou xrhsth sto arrayList
           managers.add(resultSet.getString("username"));
       return managers;
   }
   
   public List<String> getStaff()throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `username` FROM `Users` "
               + " WHERE `role` = 'STAFF' ORDER BY `username` ASC;");
       
       //xrhsh arrayList gia tin epistrofh twn xrhstwn
       List<String> staff = new ArrayList<String>();
       while(resultSet.next())//oso uparxoun grammes sto resultSet - apotelesma
           //prosthhkh neou xrhsth sto arrayList
           staff.add(resultSet.getString("username"));
       return staff;
   }
}
