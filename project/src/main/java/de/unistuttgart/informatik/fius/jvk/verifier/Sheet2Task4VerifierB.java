package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntitySpawnAction;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;

/**
 * A Verifier that checks if all coins left the playing field.
 */
public class Sheet2Task4VerifierB implements TaskVerifier {

    private BasicTaskInformation task = new BasicTaskInformation("Sheet 2 Task 4 part b)", "Collect all Coins on the playingfield.");
    private ActionLog actionLog;
    private Simulation sim;



    @Override
    public void attachToSimulation(Simulation sim) {
        // the action log should be enough to verify most tasks
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {
        if(sim.getPlayfield().getAllEntitiesOfType(new Coin().getClass(),true).size() == 0) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }

}