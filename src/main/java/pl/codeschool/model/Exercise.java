package pl.codeschool.model;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    private int id;
    private String title;
    private String description;
    private List<Solution> solutions;

    public Exercise() {
        this.solutions = new ArrayList<>();
    }

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
        this.solutions = new ArrayList<>();
    }

    public Exercise(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.solutions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

}
