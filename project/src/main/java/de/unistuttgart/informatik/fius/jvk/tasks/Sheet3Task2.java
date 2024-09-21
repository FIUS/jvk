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

import java.util.Random;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

/**
 * TODO: Description
 * @author Jannik
 */
public class Sheet3Task2 implements Task{
    @Override
    public void run(Simulation sim) {
        preparePlayingField(sim);
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Totoro totoro = new Totoro();
        pm.placeEntityAt(totoro, new Position(0, 0));

        // implement the task here
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void preparePlayingField(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        
        Shape bushes = new UnionShape(
            new Line(new Position(13, 3), new Position(13, 6)),
            new Line(new Position(-10, 7), new Position(13, 7)),
            new Line(new Position(14,3), new Position(30,3))
        );
        pm.placeEntityAtEachPosition(new BushFactory(), bushes);
            
            
        Random r = new Random();

        Integer clockwiseRounds = r.nextInt(2);

        if (clockwiseRounds > 0) {
            pm.placeEntityAt(new Nut(), new Position(2, 0));
            pm.placeEntityAt(new Nut(), new Position(2, 5));
            pm.placeEntityAt(new Nut(), new Position(-1, 5));
            pm.placeEntityAt(new Nut(), new Position(-1, 0));
        }

        pm.placeEntityAt(new Nut(), new Position(4, 0));

        Integer counterclockwiseRounds = r.nextInt(3) + 1;

        pm.placeMultipleEntitiesAt(new NutFactory(), counterclockwiseRounds + 1, new Position(4, 4));
        pm.placeMultipleEntitiesAt(new NutFactory(), counterclockwiseRounds, new Position(7, 4));
        pm.placeMultipleEntitiesAt(new NutFactory(), counterclockwiseRounds + 2, new Position(7, -1));
        pm.placeMultipleEntitiesAt(new NutFactory(), counterclockwiseRounds + 1 + r.nextInt(3), new Position(4, -1));

        
        pm.placeMultipleEntitiesAt(new NutFactory(), 3, new Position(4, 4));
        pm.placeEntityAt(new Nut(), new Position(12, 4));

        pm.placeEntityAt(new Nut(), new Position(12, 2));
        pm.placeMultipleEntitiesAt(new NutFactory(), 9, new Position(new Random().nextInt(3)+13, 2));
    }
    
}
