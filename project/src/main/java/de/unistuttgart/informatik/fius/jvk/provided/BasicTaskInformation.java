package de.unistuttgart.informatik.fius.jvk.provided;

import java.util.Collections;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;

/**
 * Class for storing task information and task state for {@link TaskVerifier}.
 * <p>
 * An object of this class is immutable and all update methods create a new object of this class!
 */
public class BasicTaskInformation implements TaskInformation {

    /** The task title. */
    private final String title;
    /** The task description. */
    private final String description;
    /** The task verification status. (Default is UNDECIDED) */
    private final TaskVerificationStatus status;
    /** The list of subtasks. */
    private final List<TaskInformation> subTasks;

    /**
     * Set title and description.
     *
     * @param title the title of the task; must not end with a newline character
     * @param description the description of the task
     */
    public BasicTaskInformation(String title, String description) {
        this(title, description, Collections.emptyList());
    }

    /**
     * Set title, description and status of the task.
     *
     * @param title the title of the task; must not end with a newline character
     * @param description the description of the task
     * @param status the current task verification status
     */
    public BasicTaskInformation(String title, String description, TaskVerificationStatus status) {
        this(title, description, status, Collections.emptyList());
    }

    /**
     * Set title, description and subtasks of the task.
     *
     * @param title the title of the task; must not end with a newline character
     * @param description the description of the task
     * @param subTasks the list of subtasks
     */
    public BasicTaskInformation(String title, String description, List<? extends TaskInformation> subTasks) {
        this(title, description, TaskVerificationStatus.UNDECIDED, subTasks);
    }

    /**
     * Set title, description, status and subtasks of the task.
     *
     * @param title the title of the task; must not end with a newline character
     * @param description the description of the task
     * @param status the current task verification status
     * @param subTasks the list of subtasks
     */
    public BasicTaskInformation(String title, String description, TaskVerificationStatus status, List<? extends TaskInformation> subTasks) {
        if (title == null) throw new NullPointerException("Task title must not be null!");
        if (description == null) throw new NullPointerException("Task description must not be null!");
        if (status == null) throw new NullPointerException("Task status must not be null!");
        if (subTasks == null) throw new NullPointerException("Subtasks list must not be null!");

        this.title = title;
        this.description = description;
        this.status = status;
        this.subTasks = Collections.unmodifiableList(subTasks);
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

    /**
     * Create a copy of this object with the new task title.
     *
     * @param title the new task title
     * @return the new TaskInformation object
     */
    public BasicTaskInformation updateTitle(String title) {
        return new BasicTaskInformation(title, this.description, this.status, this.subTasks);
    }

    /**
     * Create a copy of this object with the new task description.
     *
     * @param description the new task description
     * @return the new TaskInformation object
     */
    public BasicTaskInformation updateDescription(String description) {
        return new BasicTaskInformation(this.title, description, this.status, this.subTasks);
    }

    /**
     * Create a copy of this object with the new task verification status.
     *
     * @param status the new task status
     * @return the new TaskInformation object
     */
    public BasicTaskInformation updateStatus(TaskVerificationStatus status) {
        return new BasicTaskInformation(this.title, this.description, status, this.subTasks);
    }

    /**
     * Create a copy of this object with the new subTasks list.
     *
     * @param subTasks the new subTasks list
     * @return the new TaskInformation object
     */
    public BasicTaskInformation updateSubTasks(List<? extends TaskInformation> subTasks) {
        return new BasicTaskInformation(this.title, this.description, this.status, subTasks);
    }

}
