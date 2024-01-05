package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.factories.CoinFactory;
import de.unistuttgart.informatik.fius.jvk.provided.factories.WallFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;

import java.util.Random;

public class Chapter13Task1 implements Task{

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        buildEnvironment(pm);

        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        // Implement (sub)tasks here
        
    }

    private void buildEnvironment(PlayfieldModifier pm){
        // build the outside wall
        pm.placeEntityAtEachPosition(new WallFactory(), new Rectangle(new Position(-1, -2), new Position(10, 2)));

        Random r = new Random();
        pm.placeMultipleEntitiesAt(new CoinFactory(), (int)Math.round(r.nextDouble()*20+45), new Position(1, 0));

        for(int i = 0; i < 10; i++){
            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Wall(), new Position(i, -1));

            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Wall(), new Position(i, 1));
        }
    }
}
