package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Projects {

    List<Project> projects;

    public Projects() {
        this.projects = new ArrayList<>();
    }

    public static Projects create() {
        return new Projects();
    }

    public void add(String projectName) {
        projects.add(new Project(projectName));
    }

    public Project get(String projectName) {
        return projects.stream().filter(project -> project.name.equals(projectName)).findFirst()
                .orElse(null);
    }

    public boolean has(int taskId) {
        return projects.stream().anyMatch(project -> project.has(taskId));
    }

    public void setDone(int taskId, boolean done) {
        projects.stream().filter(project -> project.has(taskId)).findFirst()
                .ifPresent(project -> project.setDone(taskId, done));
    }

    public List<Project> getProjectsList() {
        return projects;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        getProjectsList().forEach(project -> {
            stringBuilder.append(project.getName());
            stringBuilder.append("\n");
            stringBuilder.append(project);
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }

}
