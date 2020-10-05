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
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        
        // use this neo for b), c) and d) and e)
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        
        //use this neo for f)
        Neo neoF = new Neo();
        pm.placeEntityAt(neoF, new Position(0, 2));
        
        //use this neo for g)
        Neo neoG = new Neo();
        pm.placeEntityAt(neoG, new Position(0, 4));
        
        //use this neo for h)
        Neo neoH = new Neo();
        pm.placeEntityAt(neoH, new Position(0, 6));
        
    }
    
}
