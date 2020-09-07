package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntitySpawnAction;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import java.util.List;
import java.util.ArrayList;

/**
 * A Verifier that checks if at least one Entity was spawned on the playfield.
 */
public class Sheet2Task2Verifier implements TaskVerifier {
    @Override
    public void attachToSimulation(Simulation sim) {
    }

    @Override
    public void verify() {
    }

    @Override
    public TaskInformation getTaskInformation() {
        return null;
    }

}