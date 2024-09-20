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
import de.unistuttgart.informatik.fius.jvk.provided.factories.NutFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;

/**
 * @author Sara Galle
 */
public class ExtraSheetTask4 implements Task{

    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        buildEnvironment(pm);
        Totoro totoro = new Totoro();
        totoro.setNutsInPocket(500);
        pm.placeEntityAt(totoro, new Position(1, 2));
        //your code here
        
    }
    
    private void buildEnvironment(PlayfieldModifier pm){
        // build the outside wall
        pm.placeEntityAtEachPosition(new NutFactory(), new Rectangle(new Position(0, 0), new Position(11, 4)));

        Random r = new Random();
        for(int i = 1; i < 11; i++){
            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Nut(), new Position(i, 1));

            if(r.nextDouble() < 0.4)
                pm.placeEntityAt(new Nut(), new Position(i, 3));
        }
    }
    
}
