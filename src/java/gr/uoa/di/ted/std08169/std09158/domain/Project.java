package gr.uoa.di.ted.std08169.std09158.domain;

import java.util.List;

/**
 *
 * @author Labis
 */
public class Project {
    private String name;
    private String description;
    private Visibility visibility;
    private String manager;
    private List<String> staff;

    public Project(String name, String description, Visibility visibility, String manager, List<String> staff) {
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.manager = manager;
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getManager() {
        return manager;
    }

    public List<String> getStaff() {
        return staff;
    }
}
