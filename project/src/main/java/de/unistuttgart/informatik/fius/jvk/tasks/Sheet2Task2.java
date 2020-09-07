package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;

public class Sheet2Task2 implements Task{
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        buildEnvironment(pm);
        movement(neo);
    }

    private void buildEnvironment(PlayfieldModifier pm){
        buildHorizontalWalls(-2, -1, 11, pm);
        buildVerticalWalls(-1, -1, 2, pm);
        buildHorizontalWalls(2, 0, 3, pm);
        buildHorizontalWalls(2, 7, 10, pm);
        buildVerticalWalls(11, -1, 2, pm);
        pm.placeEntityAt(new PhoneBooth(), new Position(10, 0));

        /* --------------------------- */

        /* TODO: Implement Task 2 b) */

        /* --------------------------- */
    }

    private void movement(Neo neo){
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

    private void buildHorizontalWalls(int y, int x1, int x2, PlayfieldModifier pm) throws IllegalArgumentException{
        if(x2 < x1)
            throw new IllegalArgumentException();
        for(int x = x1; x <= x2; x++)
            pm.placeEntityAt(new Wall(), new Position(x, y));
    }

    private void buildVerticalWalls(int x, int y1, int y2, PlayfieldModifier pm) throws IllegalArgumentException{
        if(y2 < y1)
            throw new IllegalArgumentException();
        for(int y = y1; y <= y2; y++)
            pm.placeEntityAt(new Wall(), new Position(x, y));
    }
}