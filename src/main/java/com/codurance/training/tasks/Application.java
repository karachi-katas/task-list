package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class Application implements Runnable {
    private static final String QUIT = "quit";
    public static final int COMMAND_INDEX = 0;
    public static final int SUBCOMMAND_INDEX = 1;

    private Projects projects = new Projects();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Application(in, out).run();
    }

    public Application(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    @Override
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[COMMAND_INDEX];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[SUBCOMMAND_INDEX]);
                break;
            case "check":
                check(commandRest[SUBCOMMAND_INDEX]);
                break;
            case "uncheck":
                uncheck(commandRest[SUBCOMMAND_INDEX]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        projects.getProjects().forEach((projectName, project) -> {
            out.println(projectName);
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        });
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            addTask(subcommandRest[1]);
        }
    }

    private void addTask(String s) {
        String[] projectTask = s.split(" ", 2);
        addTask(projectTask[0], projectTask[1]);
    }

    private void addProject(String name) {
        projects.add(name);
    }

    private void addTask(String projectName, String description) {
        Project project = projects.get(projectName);
        if (project == null) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        Task task = new Task(nextId(), description, false);
        project.add(task);
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {

        int taskId = Integer.parseInt(idString);

        if (projects.has(taskId)) {
            projects.setDone(taskId, done);
            return;
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
}
