package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;

/**
 * @author Sara Galle
 */
public class Chapter8Task2 implements Task {

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo1 = new Neo();
        Neo neo2 = new Neo();
        Neo neo3 = new Neo();
        Neo neo4 = new Neo();
        pm.placeEntityAt(neo1, new Position(2, 2));
        pm.placeEntityAt(neo2, new Position(3, 3));
        pm.placeEntityAt(neo3, new Position(2,2));
        pm.placeEntityAt(neo4, new Position(1,1));
        int random = (int)(Math.random() * 4);
        for (int i = 0; i < random; i++){
            neo1.turnClockWise();
        }
        random = (int)(Math.random() * 4);
        for (int i = 0; i < random; i++){
            neo2.turnClockWise();
        }
        random = (int)(Math.random() * 4);
        for (int i = 0; i < random; i++){
            neo3.turnClockWise();
        }
        random = (int)(Math.random() * 4);
        for (int i = 0; i < random; i++){
            neo4.turnClockWise();
        }
        random = (int)(Math.random() * 42);
        neo1.setCoinsInWallet(random);
        random = (int)(Math.random() * 42);
        neo2.setCoinsInWallet(random);
        random = (int)(Math.random() * 42);
        neo3.setCoinsInWallet(random);
        random = (int)(Math.random() * 42);
        neo4.setCoinsInWallet(random);
    }
}
