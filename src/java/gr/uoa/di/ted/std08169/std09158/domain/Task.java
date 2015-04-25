package gr.uoa.di.ted.std08169.std09158.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Labis
 */
public class Task {
    String project;
    String name;
    String description;
    Date startDate;
    Date endDate;
    State state;
    List<String> works;

    public Task(String project, String name, String description, Date startDate, Date endDate, State state, List<String> works) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.works = works;
    }
    
    public Task(String project, String name, String description, Date startDate, Date endDate, List<String> works) {
        this(project, name, description, startDate, endDate, State.UNCOMPLETED, works);
    }
    
    public String getProject() {
        return project;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public State getState() {
        return state;
    }
    
    public List<String> getWorks() {
        return works;
    } 
}
