package de.unistuttgart.informatik.fius.jvk.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

public class Sheet2Task5 implements Task{

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        buildEnvironment(pm);

        Totoro totoro = new Totoro();
        pm.placeEntityAt(totoro, new Position(0, 0));

        // Implement (sub)tasks here
        
    }

    private void buildEnvironment(PlayfieldModifier pm){
        // build the outside wall
        pm.placeEntityAtEachPosition(new BushFactory(), new Rectangle(new Position(-1, -2), new Position(10, 2)));

        Random r = new Random();
        pm.placeMultipleEntitiesAt(new NutFactory(), (int)Math.round(r.nextDouble()*20+45), new Position(1, 0));

        for(int i = 0; i < 10; i++){
            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Bush(), new Position(i, -1));

            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Bush(), new Position(i, 1));
        }
    }
}
