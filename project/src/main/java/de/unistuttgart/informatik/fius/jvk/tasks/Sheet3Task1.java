package de.unistuttgart.informatik.fius.jvk.tasks;

import java.util.Iterator;


import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.CoinFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;


public class Sheet3Task1 implements Task {
    
    @Override
    public void run(Simulation sim) {
        // Implementation here
        Neo neo = new Neo();
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        pm.placeEntityAt(neo, new Position(0, 0));
        
        
    }
    
}
