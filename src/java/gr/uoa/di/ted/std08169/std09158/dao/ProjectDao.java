package gr.uoa.di.ted.std08169.std09158.dao;

import gr.uoa.di.ted.std08169.std09158.domain.Project;
import gr.uoa.di.ted.std08169.std09158.domain.Visibility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Managarei ta projects stin vash
 */
public class ProjectDao {
    private Connection connection;

    public ProjectDao() throws SQLException, ClassNotFoundException {
        //fortwsh ths klashs tou driver
        Class.forName("com.mysql.jdbc.Driver");
        //sundesh me tin vash
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectManagement?user=root&password=root");
   }
   
   public void addProject(Project project) throws SQLException {
       connection.createStatement().executeUpdate("INSERT INTO `Projects` "
               + "(`name`, `description`, `visibility`, `manager`) "
               + "VALUES ('" + project.getName() + "', '" + project.getDescription() + "', '" + project.getVisibility() + "',"
               + "'" + project.getManager() + "');");
       
        try { //eisagwgh tou staff ston pinaka Staff
            for (String username : project.getStaff()) {//gia kathe username pou uparxei mesa sto staff
                connection.createStatement().executeUpdate("INSERT INTO `Staff` "
                        + "(`user`, `project`) "           //onoma project
                        + "VALUES ('" + username + "', '" + project.getName() + "');");
            }
        } catch (SQLException ex) { //kapoia username apo to staff den bikan swsta, opote
            try {
                //diagrafetai oti staff exei bei ston pinaka Staff sumfwna me to project name.
                connection.createStatement().executeUpdate("DELETE FROM `Staff` "
                        + "WHERE `project` = '" + project.getName() + "';");
                //digrafetai kai to idio to project apo ton pinaka Projects me vash to name
                connection.createStatement().executeUpdate("DELETE FROM `Projects` "
                        + "WHERE `name` = '" + project.getName() + "';");
            } catch (SQLException e) {} //se periptwsh pou skasoun ta panw delete
            throw ex; //petaei telika to exception
        }
   }
   
   public List<String> getProjects() throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `name` FROM `Projects` "
               + "ORDER BY `name` ASC;"); //epistrefontai me alfavitikh seira
       
       List<String> projects = new ArrayList<String>();
       //Gemisma listas me ta projects
       while(resultSet.next())
           projects.add(resultSet.getString("name"));
       
       return projects;
   }
   
   public Project getProject(String name) throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `Projects` "
               + "WHERE `name` = '" + name + "';");

        ResultSet staffResultSet = connection.createStatement().executeQuery("SELECT `user` FROM `Staff` "
               + "WHERE `project` = '" + name + "';");
        
       
        List<String> staff = new ArrayList<String>();
        while(staffResultSet.next())
            //topothetish stin lista to staff (ta usernames tous)
            staff.add(staffResultSet.getString("user"));
        
       return resultSet.next() ? new Project(name, resultSet.getString("description"), 
               Visibility.valueOf(resultSet.getString("visibility")), resultSet.getString("manager"), staff) : null;
   }
   
   public void editProject(Project project) throws SQLException {
       connection.createStatement().executeUpdate("UPDATE `Projects` "
               + "SET `description` = '" + project.getDescription() + "', "
               + "`visibility` = '" + project.getVisibility() + "', `manager` = '" + project.getManager() + "'"
               + "WHERE `name` = '" + project.getName() + "';");
       
       connection.createStatement().executeUpdate("DELETE FROM `Staff` "
                       + "WHERE `project` = '" + project.getName() + "';");
       
        for(String username : project.getStaff()) { //PROVLIMA: den kanei 2 atoma staff edit.......
            connection.createStatement().executeUpdate("INSERT INTO `Staff` "
                    + "(`user`, `project`) "
                    + "VALUES ('" + username + "', '" + project.getName() + "');");
        }
   }
   
   public void deleteProject(String project) throws SQLException {
        //prwta diagrafh twn Staff tou project
        connection.createStatement().executeUpdate("DELETE FROM `Staff` "
                + "WHERE `project` = '" + project + "';");

        //diagrafh tou idiou tou project
        connection.createStatement().executeUpdate("DELETE FROM `Projects` "
                + "WHERE `name` = '" + project + "';");
   }
   
   public List<String> getUserProjects(String username) throws SQLException {
       // -> periptwsh pou o username einai manager
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `name` FROM `Projects` "
               + "WHERE `manager` = '" + username + "' ORDER BY `name` ASC;");
       
       List<String> projects = new ArrayList<String>();
       
       while (resultSet.next()) { //gemisma listas twn projects pou einai kapoios manager
           projects.add(resultSet.getString("name"));
       }
       
       // -> periptwsh pou o username einai staff
       //ta project pou einai autos staff
       resultSet = connection.createStatement().executeQuery("SELECT `project` FROM `Staff` "
               + "WHERE `user` = '" + username + "' ORDER BY `project` ASC;");
       
       //sunexish gemismatos tis listas me projects pou einai kapoios staff
       while (resultSet.next()) { 
           projects.add(resultSet.getString("project"));
       }

       return projects;
   }
   
   public List<String> getPublicProjects(String username) throws SQLException { 
       // epistrefei ta public projects pou o xrhsths den einai oute manager oute staff gia to sugkekrimeno project
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `name` FROM `Projects` "
               + "WHERE `visibility` = 'PUBLIC' AND `manager` != '" + username + "' AND "
               + "NOT EXISTS (SELECT * FROM `Staff` WHERE `user` = '" + username + "' AND `project` = `name`) ORDER BY `name` ASC;");
       
       List<String> projects = new ArrayList<String>();
       
       //gemisma listas twn projects pou einai public kai o xrhsths den einai oute manager oute staff
       while (resultSet.next()) { 
           projects.add(resultSet.getString("name"));
       }
       
       return projects;
   }
   
   public List<String> getStaff(String projectName)throws SQLException {
       ResultSet resultSet = connection.createStatement().executeQuery("SELECT `user` FROM `Staff` "
               + " WHERE `project` = '" + projectName + "' ORDER BY `user` ASC;");
       
       //xrhsh arrayList gia tin epistrofh twn xrhstwn
       List<String> staff = new ArrayList<String>();
       while(resultSet.next())//oso uparxoun grammes sto resultSet - apotelesma
           //prosthhkh neou xrhsth sto arrayList
           staff.add(resultSet.getString("user"));
       return staff;
   }
}