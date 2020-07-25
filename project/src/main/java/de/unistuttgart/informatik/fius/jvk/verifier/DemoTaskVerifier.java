package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntitySpawnAction;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;


public class DemoTaskVerifier implements TaskVerifier {

    private BasicTaskInformation task = new BasicTaskInformation("Demo Task", "Spawn any Entity to complete the task.");

    private ActionLog actionLog;

    private boolean needChecking = true;

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
    }

    @Override
    public void verify() {
        if (this.needChecking) {
            if (this.actionLog.getActionsOfType(EntitySpawnAction.class, true).isEmpty()) {
                return; // no entity was spawned
            }
            // list of spawn actions was not empty => an entity was spawned => task is solved
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.needChecking = false; // allow short circuit for following calls to verify
        }
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }

}