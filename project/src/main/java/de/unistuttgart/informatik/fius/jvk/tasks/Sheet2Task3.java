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
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;

/**
 * This is the task for exercise 3 on sheet 2
 * @author paulesn
 */
public class Sheet2Task3 implements Task{
    
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        pm.placeEntityAt(new Nut(), new Position(0, 0));
        pm.placeEntityAt(new Nut(), new Position(0, 1));
        pm.placeEntityAt(new Nut(), new Position(0, 2));
        pm.placeEntityAt(new Nut(), new Position(1, 2));
        pm.placeMultipleEntitiesAt(new NutFactory(), 2, new Position(2, 2));

        Totoro totoro = new Totoro();
        pm.placeEntityAt(totoro, new Position(0, 0));

        // implement task here
        
    }
}
