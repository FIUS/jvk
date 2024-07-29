package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;

/**
 * @author Sara Galle
 */
public class Chapter8Task1 implements Task {

    private void dropMultipleCoins(Neo neo, int n){
        for (int i = 0; i < n; i++){
            neo.dropCoin();
        }
    }

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(2, 2));
        neo.setCoinsInWallet(9999);
        int randomNum1 = (int)(Math.random() * 6) + 1;
        for (int i = 0; i < randomNum1; i++){
            pm.placeEntityAt(new Coin(), new Position(1,2));
        }
        int randomNum2 = (int)(Math.random() * 6) + 1;
        for (int i = 0; i < randomNum2; i++){
            pm.placeEntityAt(new Coin(), new Position(3,2));
        }
        // with this Neo will drop 5 coins on his current position
        this.dropMultipleCoins(neo, 5);
        // put your implementation below this comment
    }
}