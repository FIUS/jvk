package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

public class Sheet2Task7 implements Task {

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Totoro totoro1 = new Totoro();
        Totoro totoro2 = new Totoro();
        Totoro totoro3 = new Totoro();
        Totoro totoro4 = new Totoro();
        pm.placeEntityAt(totoro1, new Position(0, 2));
        pm.placeEntityAt(totoro4, new Position(0, 0));
        pm.placeEntityAt(totoro3, new Position(2, 2));
        pm.placeEntityAt(totoro2, new Position(2, 0));
        totoro4.setNutsInPocket(42);
        totoro2.setNutsInPocket(42);
        totoro1.turnClockWise();
        totoro3.setNutsInPocket(13);
        totoro3.turnClockWise();
        totoro1.setNutsInPocket(7);
        totoro3.turnClockWise();


    }

}
