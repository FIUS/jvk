package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationClock;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.MovableEntity;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Bush;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Home_Tree;


public class Sheet2Task2Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select This Task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Cause an Exception", "Cause an Exception by running Totoro into a bush.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Paper only Task", "See the task sheet for details.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Stop Totoro", "Stop Totoro before he collides with the wall.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e) Stop the Exception", "Stop the Exception in other creative ways.");

    private ActionLog actionLog;
    private SimulationClock clock;

    public Sheet2Task2Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        this.task = new BasicTaskInformation("Sheet 2 Task 2", "Learn about Exceptions.", subTasks);

    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.clock = sim.getSimulationClock();
        this.actionLog = sim.getActionLog();
    }

    @Override
    public void verify() {
        System.out.println("Verify");
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        Optional<Entity> maybePlayer = spawnActions.stream()
            .map((action) -> action.getEntity())
            .filter((entity) -> (entity instanceof MovableEntity))
            .findFirst();
        
        Optional<Entity> maybeGoal = spawnActions.stream()
            .map((action) -> action.getEntity())
            .filter((entity) -> (entity instanceof Home_Tree))
            .findFirst();
        
        Optional<Entity> maybeWall = spawnActions.stream()
                .filter((action) -> {
                    Position pos = action.getPosition();
                    if (pos.getY() != 0) return false;
                    if (pos.getX() <= 0 || pos.getX() >= 10) return false;
                    return true; // only spawns between player and goal!
                })
                .map((action) -> action.getEntity())
                .filter((entity) -> (entity instanceof Bush))
                .findFirst();

        if (maybePlayer.isEmpty() || maybeGoal.isEmpty()) {
            return; // no subtask needs to change if no player and goal is spawned!
        }

        Entity player = maybePlayer.get();
        Position goal = maybeGoal.get().getPosition();

        boolean reachedGoal = this.actionLog.getActionsOfTypeOfEntity(player, EntityMoveAction.class, true).stream()
            .anyMatch(action -> action.to().equals(goal));

        boolean shouldHaveReachedGoal = this.clock.getLastTickNumber() >= 42;

        // subtask b) Player is spawned here
        if (maybeWall.isPresent() && !reachedGoal) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL); 
        }
        
        // subtask d)
        int moveCount = this.actionLog.getActionsOfTypeOfEntity(player, EntityMoveAction.class, true).size();
        if (moveCount < 10 && maybeWall.isPresent() && !reachedGoal && !shouldHaveReachedGoal) {
            String remaining = "(remaining: " + (42 - this.clock.getLastTickNumber()) + ")";
            String hint = "Waiting some time to make sure the player should have reached the goal by now " + remaining;
            this.taskD = this.taskD.updateDescription("Stop the player before he collides with the wall.\n" + hint);
        }
        if (moveCount < 10 && maybeWall.isPresent() && !reachedGoal && shouldHaveReachedGoal) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL); // also assume c) solved
            this.taskD = this.taskD.updateDescription("Stop the player before he collides with the wall.");
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        // subtask e)
        if (maybeWall.isPresent() && reachedGoal) {
            // goal reached even though wall is present
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL); // also assume c) solved
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL); // also assume d) solved
        }
        int turnCount = this.actionLog.getActionsOfTypeOfEntity(player, EntityTurnAction.class, true).size();
        if (maybeWall.isPresent() && turnCount > 0) {
            // assume player turned away before hitting wall
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL); // also assume c) solved
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL); // also assume d) solved
        } 

        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
    }


    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }



}
