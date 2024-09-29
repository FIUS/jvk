package de.unistuttgart.informatik.fius.jvk.provided.programs;

import de.unistuttgart.informatik.fius.icge.simulation.programs.Program;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Totoro;


public class SafeWalkProgram implements Program<Totoro> {

    @Override
    public void run(Totoro totoro) {
        if (totoro.canMove()) {
            totoro.move();
        }
    }
}
