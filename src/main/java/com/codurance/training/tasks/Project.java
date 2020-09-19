package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Project {

    List<Task> tasks;

    public Project(String name) {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Optional<Task> getTaskBy(int id){
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }
}
