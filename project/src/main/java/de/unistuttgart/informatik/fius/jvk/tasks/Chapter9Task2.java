package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;

/**
 * @author Sara Galle
 */
public class Chapter9Task2 implements Task {

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        neo.setCoinsInWallet(9999);

        neo.turnClockWise();
        neo.move();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.move();
        neo.dropCoin();
        neo.turnClockWise();
        neo.move();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.move();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.move();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
        neo.dropCoin();
        neo.move();
        neo.turnClockWise();
    }
}
