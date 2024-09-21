package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;


public class Sheet2Task4Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Handle the Exception", "Avoid the Exception from Exercise 2");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Test the IF-Condition", "Delete the wall and run the execution again.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Add an ELSE-Block", "Reach nut even if there is an obstacle.");

    private ActionLog actionLog;
    private Simulation sim;

    public Sheet2Task4Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        this.task = new BasicTaskInformation("Sheet 2 Task 3", "What IF there is a Bush?", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {
        if(testTaskB()||testTaskC()) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(testTaskC()) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(testTaskD()) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
    }

    private boolean testTaskB(){
        if(sim.getPlayfield().getEntitiesAt(new Position(5, 0)).isEmpty())
            return false;
        List<EntityMoveAction> moveActions = this.actionLog.getActionsOfType(EntityMoveAction.class, true);
        if(moveActions.size() != 4)
            return false;
        return true;
    }

    private boolean testTaskC(){
        if(!sim.getPlayfield().getEntitiesAt(new Position(5, 0)).isEmpty())
            return false;
        List<EntityMoveAction> moveActions = this.actionLog.getActionsOfType(EntityMoveAction.class, true);
        if(moveActions.size() != 10)
            return false;
        return true;
    }

    private boolean testTaskD(){
        if(sim.getPlayfield().getEntitiesAt(new Position(5, 0)).isEmpty())
            return false;
        List<EntityMoveAction> moveActions = this.actionLog.getActionsOfType(EntityMoveAction.class, true);
        Position goal = new Position(10,0);
        boolean reachedGoal = moveActions.stream().anyMatch(action -> action.to().equals(goal));
        if(!reachedGoal) return false;
        return true;
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }



}
