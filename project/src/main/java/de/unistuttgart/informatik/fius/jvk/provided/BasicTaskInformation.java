package de.unistuttgart.informatik.fius.jvk.provided;

import java.util.Collections;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;


public class BasicTaskInformation implements TaskInformation {

    private final String title;
    private final String description;
    private final TaskVerificationStatus status;
    private final List<TaskInformation> subTasks;

    public BasicTaskInformation(String title, String description) {
        this(title, description, Collections.emptyList());
    }

    public BasicTaskInformation(String title, String description, TaskVerificationStatus status) {
        this(title, description, status, Collections.emptyList());
    }

    public BasicTaskInformation(String title, String description, List<TaskInformation> subTasks) {
        this(title, description, TaskVerificationStatus.UNDECIDED, subTasks);
    }

    public BasicTaskInformation(String title, String description, TaskVerificationStatus status, List<TaskInformation> subTasks) {
        if (title == null) throw new NullPointerException("Task title must not be null!");
        if (description == null) throw new NullPointerException("Task description must not be null!");
        if (status == null) throw new NullPointerException("Task status must not be null!");
        if (subTasks == null) throw new NullPointerException("Subtasks list must not be null!");

        this.title = title;
        this.description = description;
        this.status = status;
        this.subTasks = subTasks;
    }

    @Override
    public String getTaskTitle() {
        return this.title;
    }

    @Override
    public String getTaskDescription() {
        return this.description;
    }

    @Override
    public TaskVerificationStatus getTaskStatus() {
        return this.status;
    }

    @Override
    public List<TaskInformation> getChildTasks() {
        return this.subTasks;
    }

    public BasicTaskInformation updateTitle(String title) {
        return new BasicTaskInformation(title, this.description, this.status, this.subTasks);
    }

    public BasicTaskInformation updateDescription(String description) {
        return new BasicTaskInformation(this.title, description, this.status, this.subTasks);
    }

    public BasicTaskInformation updateStatus(TaskVerificationStatus status) {
        return new BasicTaskInformation(this.title, this.description, status, this.subTasks);
    }

    public BasicTaskInformation updateSubTasks(List<TaskInformation> subTasks) {
        return new BasicTaskInformation(this.title, this.description, this.status, subTasks);
    }

}