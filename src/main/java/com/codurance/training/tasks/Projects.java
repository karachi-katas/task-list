package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class Projects {

    private Map<String, Project> projects;

    public Projects() {
        this.projects = new LinkedHashMap<>();
    }

    public void add(String projectName) {
        this.projects.put(projectName, new Project());
    }

    public Project get(String projectName) {
        return projects.getOrDefault(projectName, null);
    }

    public boolean has(int taskId) {
        return projects.entrySet().stream().anyMatch(entry -> entry.getValue().has(taskId));
    }

    public void setDone(int taskId, boolean done) {
        projects.entrySet().stream().filter(entry -> entry.getValue().has(taskId)).findFirst()
                .ifPresent(entry -> entry.getValue().setDone(taskId, done));
    }

    public Map<String, Project> getProjects() {
        return projects;
    }
}
