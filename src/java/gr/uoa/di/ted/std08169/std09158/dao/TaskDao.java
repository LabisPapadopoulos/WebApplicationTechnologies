package gr.uoa.di.ted.std08169.std09158.dao;

import gr.uoa.di.ted.std08169.std09158.domain.Comment;
import gr.uoa.di.ted.std08169.std09158.domain.State;
import gr.uoa.di.ted.std08169.std09158.domain.Task;
import gr.uoa.di.ted.std08169.std09158.domain.TaskSummary;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Labis
 */
public class TaskDao {
    private Connection connection;
    private PreparedStatement addTask;
    private PreparedStatement addWorks;
    private PreparedStatement getTasks;
    private PreparedStatement getTask;
    private PreparedStatement getWorks;
    private PreparedStatement addComment;
    private PreparedStatement getComments;
    private PreparedStatement getUserTasks;
    private PreparedStatement editTaskState;
    
    public TaskDao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectManagement?user=root&password=root");
        //Den kanei automata epikurwsh dosolhpsias meta to telos kathe eperwtishs. Perimenei na tou poume pote na kanei
        //commit 'h rollback(askurwsh)
        connection.setAutoCommit(false);
        //proetoimazei to statement pou tha ektelestei
        addTask = connection.prepareStatement("INSERT INTO `Tasks` "
                + "(`project`, `name`, `description`, `startDate`, "
                + "`endDate`, `state`) VALUES (?, ?, ?, ?, ?, ?);");
        addWorks = connection.prepareStatement("INSERT INTO `Works` "
                + "(`user`, `project`, `task`) VALUES (?, ?, ?);");
        getTasks = connection.prepareStatement("SELECT `name` FROM `Tasks` "
                + "WHERE `project` = ? ORDER BY `name` ASC;");
        getTask = connection.prepareStatement("SELECT `description`, `startDate`, `endDate`, `state` "
                + "FROM `Tasks` WHERE `project` = ? AND `name` = ?;");
        getWorks = connection.prepareStatement("SELECT `user` FROM `Works` "
                + "WHERE `project` = ? AND `task` = ? ORDER BY `user` ASC;");
        addComment = connection.prepareStatement("INSERT INTO `Comments` "
                + "(`user`, `project`, `task`, `date`, `text`) "
                + "VALUES (?, ?, ?, ?, ?);");
        getComments = connection.prepareStatement("SELECT * FROM `Comments` "
                + "WHERE `project` = ? AND `task` = ? ORDER BY `date` DESC;");
        //epistrefei ta (project, task) ta opoia den einai oloklhrwmena
        getUserTasks = connection.prepareStatement("SELECT `Works`.`project`, `Works`.`task`"
                + "FROM `Tasks`, `Works` WHERE `user` = ? AND "
                + "`Works`.`project` = `Tasks`.`project` AND `Works`.`task` = `Tasks`.`name`"
                + "AND `Tasks`.`state` = 'UNCOMPLETED' ORDER BY `Works`.`project`, `Works`.`task` ASC;");
        editTaskState = connection.prepareStatement("UPDATE `Tasks` SET `state` = ? "
                + "WHERE `project` = ? AND `name` = ?;");
        
    }
    
    public void addTask(Task task) throws SQLException {
        try {
            addTask.setString(1, task.getProject());
            addTask.setString(2, task.getName());
            addTask.setString(3, task.getDescription());
            //me to getTime epistrefei long kai gurnaei to date se timestamp
            addTask.setDate(4, new Date(task.getStartDate().getTime()));
            addTask.setDate(5, new Date(task.getEndDate().getTime()));
            addTask.setString(6, task.getState().toString());
            addTask.executeUpdate(); //ektelesh eperwthmatos
            //topothetish twn statherwn parametrwn mia fora
            addWorks.setString(2, task.getProject());
            addWorks.setString(3, task.getName());
            //eisagwgh mono twn xrhstwn
            for (String works : task.getWorks()) {
                addWorks.setString(1, works); //o xrhsths pou douleuei
                addWorks.executeUpdate();
            }
            //Otan ftasei edw exoun ginei ola kala, opote kanei epikurwsh (commit)
            connection.commit();
        } catch (SQLException ex) {
            //akurwnei oti exei kanei
            connection.rollback();
            throw ex;
        }
    }
    
    public List<String> getTasks(String project) throws SQLException {
        getTasks.setString(1, project);
        ResultSet result = getTasks.executeQuery();
        List<String> list = new ArrayList<String>();
        while (result.next()) {
            list.add(result.getString("name"));
        }
        return list;
    }
    
    //Lista apo TaskSummary (pou exei mesa mono project tou task kai onoma tou task)
    public List<TaskSummary> getUserTasks(String user) throws SQLException {
        getUserTasks.setString(1, user);
        ResultSet result = getUserTasks.executeQuery();
        
        List<TaskSummary> list = new ArrayList<TaskSummary>();
        while (result.next()) {
            list.add(new TaskSummary(result.getString("project"), result.getString("task")));
        }
        return list;
    }
    
    public Task getTask(String project, String task) throws SQLException {
        getTask.setString(1, project);
        getTask.setString(2, task);
        ResultSet result = getTask.executeQuery();
        
        //ektelesh 2hs eperwthshs gia tous works
        getWorks.setString(1, project);
        getWorks.setString(2, task);
        ResultSet worksResult = getWorks.executeQuery();
        List<String> works = new ArrayList<String>();
        while (worksResult.next()) {
            works.add(worksResult.getString("user"));
        }
        return result.next() ? new Task(project, task, result.getString("description"), 
                result.getDate("startDate"), result.getDate("endDate"), 
                State.valueOf(result.getString("state")), works) : null;
    }
    
    public void addComment(Comment comment) throws SQLException {
        try {
            addComment.setString(1, comment.getUser());
            addComment.setString(2, comment.getProject());
            addComment.setString(3, comment.getTask());
            addComment.setTimestamp(4, new Timestamp(comment.getDate().getTime())); 
            addComment.setString(5, comment.getText());
            addComment.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }
    
    public List<Comment> getComments(String project, String task) throws SQLException {
        getComments.setString(1, project);
        getComments.setString(2, task);
        ResultSet resultSet = getComments.executeQuery();
        
        List<Comment> comments = new ArrayList<Comment>();
        
        while(resultSet.next()) {
            comments.add(new Comment(resultSet.getString("user"), project, task, 
                    resultSet.getTimestamp("date"), resultSet.getString("text")));
        }
        
        return comments;
    }
    
    public void editTask(String project, String taskName, State state) throws SQLException {
        try {
            editTaskState.setString(1, state.toString());
            editTaskState.setString(2, project);
            editTaskState.setString(3, taskName);
            editTaskState.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }
}
