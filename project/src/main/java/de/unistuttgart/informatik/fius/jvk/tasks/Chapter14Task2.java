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
import de.unistuttgart.informatik.fius.jvk.provided.factories.CoinFactory;
import de.unistuttgart.informatik.fius.jvk.provided.factories.WallFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Shape;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.UnionShape;

import java.util.Random;

/**
 * TODO: Description
 * @author Jannik
 */
public class Chapter14Task2 implements Task{
    @Override
    public void run(Simulation sim) {
        preparePlayingField(sim);
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        sim.getSimulationClock().setPeriod(1);

        // implement the task here
        int steps=0;
        while(neo.getCurrentlyCollectableCoins().size()!=9){
            if(neo.getCurrentlyCollectableCoins().size()==1){
                neo.turnClockWise();
                neo.collectCoin();
            }else if(neo.getCurrentlyCollectableCoins().size()>1){
                neo.turnClockWise();
                neo.turnClockWise();
                neo.turnClockWise();
                neo.collectCoin();
            }
            if(neo.canMove()){
                neo.move();
                steps++;
                System.out.println("steps taken: "+steps);
            }else {
                neo.turnClockWise();
                neo.turnClockWise();
            }

            if(steps>=70){
                break;
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void preparePlayingField(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        
        Shape walls = new UnionShape(
            new Line(new Position(13, 3), new Position(13, 6)),
            new Line(new Position(-10, 7), new Position(13, 7)),
            new Line(new Position(14,3), new Position(30,3))
        );
        pm.placeEntityAtEachPosition(new WallFactory(), walls);
            
            
        Random r = new Random();

        Integer clockwiseRounds = r.nextInt(2);

        if (clockwiseRounds > 0) {
            pm.placeEntityAt(new Coin(), new Position(2, 0));
            pm.placeEntityAt(new Coin(), new Position(2, 5));
            pm.placeEntityAt(new Coin(), new Position(-1, 5));
            pm.placeEntityAt(new Coin(), new Position(-1, 0));
        }

        pm.placeEntityAt(new Coin(), new Position(4, 0));

        Integer counterclockwiseRounds = r.nextInt(3) + 1;

        pm.placeMultipleEntitiesAt(new CoinFactory(), counterclockwiseRounds + 1, new Position(4, 4));
        pm.placeMultipleEntitiesAt(new CoinFactory(), counterclockwiseRounds, new Position(7, 4));
        pm.placeMultipleEntitiesAt(new CoinFactory(), counterclockwiseRounds + 2, new Position(7, -1));
        pm.placeMultipleEntitiesAt(new CoinFactory(), counterclockwiseRounds + 1 + r.nextInt(3), new Position(4, -1));

        
        pm.placeMultipleEntitiesAt(new CoinFactory(), 3, new Position(4, 4));
        pm.placeEntityAt(new Coin(), new Position(12, 4));

        pm.placeEntityAt(new Coin(), new Position(12, 2));
        pm.placeMultipleEntitiesAt(new CoinFactory(), 9, new Position(new Random().nextInt(3)+13, 2));
    }
    
}
