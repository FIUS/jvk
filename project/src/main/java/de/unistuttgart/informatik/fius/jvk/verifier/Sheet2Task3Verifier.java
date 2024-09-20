package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntityDespawnAction;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntitySpawnAction;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Nut;

/**
 * A Verifier that checks if all coins left the playing field.
 */
public class Sheet2Task3Verifier implements TaskVerifier {

    //private BasicTaskInformation task = new BasicTaskInformation("Sheet 2 Task 4 part b)", "Collect all Coins on the playingfield.");
    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Collect all Nuts", "Collect all nutss on the playfield.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Drop all Nuts", "Drop all your nuts onto the field (3,3).");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Paper only task", "See the task sheet for details.");

    private ActionLog actionLog;
    private Simulation sim;

    private Position dropPosition = new Position(3, 3);

    public Sheet2Task3Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        this.task = new BasicTaskInformation("Sheet 2 Task 4", "Pickup and drop nuts.", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        // the action log should be enough to verify most tasks
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {

        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        long nrOfNuts = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .distinct()
            .count();

        boolean allCollected = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .map((nut) -> this.actionLog.getActionsOfTypeOfEntity(nut, EntityDespawnAction.class, true))
            .allMatch((despawns) -> despawns.size() == 1);

        boolean allDroppedOnPosition = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .map((nut) -> this.actionLog.getActionsOfTypeOfEntity(nut, EntitySpawnAction.class, true))
            .allMatch((spawns) -> spawns.stream().anyMatch(spawn -> spawn.getPosition().equals(this.dropPosition)));

        
        if(allCollected) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(allDroppedOnPosition) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(allCollected && allDroppedOnPosition) {
            // assume task is done
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

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }

}
