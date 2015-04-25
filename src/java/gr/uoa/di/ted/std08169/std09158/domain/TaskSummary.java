package gr.uoa.di.ted.std08169.std09158.domain;

/**
 *
 * @author Labis
 */
public class TaskSummary {
    String project;
    String name;

    public TaskSummary(String project, String name) {
        this.project = project;
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public String getName() {
        return name;
    }
}
