/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.factories.CoinFactory;
import de.unistuttgart.informatik.fius.jvk.provided.factories.WallFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;

import java.util.Random;


/**
 * @author Sara Galle
 */
public class Chapter13Task2 implements Task {
    
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        // put your implementation below this comment
        buildEnvironment(pm);
        Neo neoA = new Neo();
        Neo neoB = new Neo();
        neoA.setCoinsInWallet(1);
        pm.placeEntityAt(neoA,new Position(1,1));
        pm.placeEntityAt(neoB,new Position(1,3));

        while (true) {
            //a)
        }


        //b)
    }

    private void buildEnvironment(PlayfieldModifier pm){
        // build the outside wall
        pm.placeEntityAtEachPosition(new WallFactory(), new Rectangle(new Position(0, 0), new Position(15, 4)));
        Random r = new Random();
        for(int i = 2; i < 14; i++){
            pm.placeMultipleEntitiesAt(new CoinFactory(), (int)Math.round(r.nextDouble()*9), new Position(i, 3));
        }
        pm.placeEntityAt(new Coin(),new Position(14,1));
        pm.placeEntityAt(new Coin(),new Position(14,3));
    }
}
