package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Projects2 extends Projects {

    List<Project> projects;

    public Projects2() {
        this.projects = new ArrayList<>();
    }

    @Override
    public void add(String projectName) {

        super.add(projectName);
    }

    @Override
    public Project get(String projectName) {
        return super.get(projectName);
    }

    @Override
    public boolean has(int taskId) {
        return super.has(taskId);
    }

    @Override
    public void setDone(int taskId, boolean done) {
        super.setDone(taskId, done);
    }

    @Override
    public Map<String, Project> getProjects() {
        return super.getProjects();
    }
}
