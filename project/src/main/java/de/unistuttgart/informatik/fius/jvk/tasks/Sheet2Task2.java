package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

public class Sheet2Task2 implements Task{
    @Override
    public void run(Simulation sim) {
        //build environment for the task
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));        

        pm.placeEntityAtEachPosition(new WallFactory(), new Line(new Position(-1, -2), new Position(11, -2)));
        pm.placeEntityAtEachPosition(new WallFactory(), new Line(new Position(0, 2), new Position(3, 2)));
        pm.placeEntityAtEachPosition(new WallFactory(), new Line(new Position(7, 2), new Position(10, 2)));
        pm.placeEntityAtEachPosition(new WallFactory(), new Line(new Position(-1, -1), new Position(-1, 2)));
        pm.placeEntityAtEachPosition(new WallFactory(), new Line(new Position(11, -1), new Position(11, 2)));
        pm.placeEntityAt(new PhoneBooth(), new Position(10, 0));

        /* --------------------------- */

        /* TODO: Implement Task 2 b) */

        /* --------------------------- */
                
        /* TODO: Modify for Task 2 d), e), and g) */

        neo.move();
        neo.move();
        neo.move();
        neo.move();
        neo.move();
        neo.move();
        neo.move();
        neo.move();
        neo.move();

        /* --------------------------- */
    }
}
