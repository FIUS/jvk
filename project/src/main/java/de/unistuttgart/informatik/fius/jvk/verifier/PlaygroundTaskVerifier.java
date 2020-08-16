package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;

/**
 * The verifier of the Playground Task.
 *
 * Because the task is just for experimenting this verifier always returns the SUCCESSFUL status.
 */
public class PlaygroundTaskVerifier implements TaskVerifier {

    private BasicTaskInformation task = new BasicTaskInformation("Playground Task", "This task is just for free experimentation.", TaskVerificationStatus.SUCCESSFUL);


    @Override
    public void attachToSimulation(Simulation sim) {
        // nothing to do
    }

    @Override
    public void verify() {
        // nothing to do
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }

}