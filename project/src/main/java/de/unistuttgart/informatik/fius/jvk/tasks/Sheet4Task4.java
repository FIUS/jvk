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
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.factories.WallFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;


/**
 * @author Fabian Bühler
 */
public class Sheet4Task4 implements Task {
    
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());

        pm.placeEntityAtEachPosition(new WallFactory(), new Rectangle(new Position(-1, -1), new Position(21, 5)));

        Neo neoA = new Neo();
        pm.placeEntityAt(neoA, new Position(0, 0));

        Neo neoB = new Neo();
        pm.placeEntityAt(neoB, new Position(0, 2));
        
        Neo neoC = new Neo();
        pm.placeEntityAt(neoC, new Position(0, 4));
        
        // implement task below this line
    }
    
}
