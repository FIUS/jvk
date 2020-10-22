package de.unistuttgart.informatik.fius.jvk.provided.programs;

import de.unistuttgart.informatik.fius.icge.simulation.programs.Program;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;


public class SafeWalkProgram implements Program<Neo> {
    @Override
    public void run(Neo neo) {
        if (neo.canMove()) {
            neo.move();
        }
    }
}
