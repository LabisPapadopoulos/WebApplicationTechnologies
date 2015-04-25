package gr.uoa.di.ted.std08169.std09158.domain;

import java.util.Date;

/**
 *
 * @author Labis
 */
public class Comment {
    String user;
    String project;
    String task;
    Date date;
    String text;

    public Comment(String user, String project, String task, Date date, String text) {
        this.user = user;
        this.project = project;
        this.task = task;
        this.date = date;
        this.text = text;
    }
    
    public Comment(String user, String project, String task, String text) {
        //new Date(): epistrefei tin trexousa hmeromhnia
        this(user, project, task, new Date(), text);
    }

    public String getUser() {
        return user;
    }

    public String getProject() {
        return project;
    }

    public String getTask() {
        return task;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
