package de.unistuttgart.informatik.fius.jvk.tasks;

import java.util.List;
import java.util.Random;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Shape;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.UnionShape;

public class Sheet2Task5 implements Task{

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        buildEnvironment(pm);

        Neo player = new Neo();
        pm.placeEntityAt(player, new Position(0, 4));

        // Implement (sub)tasks here
    }

    private void buildEnvironment(PlayfieldModifier pm){
        // build the outside wall
        pm.placeEntityAtEachPosition(() -> new Wall(), new Rectangle(new Position(-1, -1), new Position(10, 10))); // FIXME use wall factory
        //buildRectangle(-1, -1, 10, 10);

        Random r = new Random();
        for(int i = 1; i < 9; i++){
            int nextAmout = 1 + r.nextInt(8); // random int between [1, 9] (both inclusive)
            pm.placeMultipleEntitiesAt(() -> new Coin(), nextAmout, new Position(i, 4)); // FIXME use coin factory
        }


        /*
        if(this.subTask == SubTask.G){*/
            pm.placeEntityAt(new Wall(), new Position(r.nextInt(8)+ 1, 3));
            pm.placeEntityAt(new Wall(), new Position(r.nextInt(8)+ 1, 3));
            pm.placeEntityAt(new Wall(), new Position(r.nextInt(8)+ 1, 5));
            pm.placeEntityAt(new Wall(), new Position(r.nextInt(8)+ 1, 5));
        /*}*/
    }
}