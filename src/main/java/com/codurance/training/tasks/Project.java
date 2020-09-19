package com.codurance.training.tasks;

import java.util.List;

public class Project {

    String name;
    Tasks tasks = new Tasks();

    public String getName() {
        return name;
    }

    public Project(String name) {
        this.name = name;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    public boolean has(int taskId) {
        return tasks.has(taskId);
    }

    public void setDone(int taskId, boolean done) {
        tasks.setDone(taskId, done);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

}
