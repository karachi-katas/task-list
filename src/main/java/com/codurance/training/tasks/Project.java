package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project {

    String name;
    List<Task> tasks;

    public Project(String name) {
        this.tasks = new ArrayList<>();
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
