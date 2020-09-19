package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tasks {

    List<Task> tasks;

    Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean has(int taskId) {
        return tasks.stream().anyMatch(task -> task.getId() == taskId);
    }

    private Optional<Task> getTaskBy(int id){
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    public void setDone(int taskId, boolean done) {
        Optional<Task> task = getTaskBy(taskId);
        task.ifPresent(t -> t.setDone(done));
    }
}
