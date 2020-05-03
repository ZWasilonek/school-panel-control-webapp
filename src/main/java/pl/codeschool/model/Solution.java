package pl.codeschool.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Solution {

    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution() {}

    public Solution(User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;
        this.created = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return formatTime(created);
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getUpdated() {
        return formatTime(updated);
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    private String formatTime(LocalDateTime dateTime) {
        String date = (DateTimeFormatter.ISO_DATE).format(dateTime);
        String time = (DateTimeFormatter.ISO_LOCAL_TIME).format(dateTime);
        return date + " " + time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
