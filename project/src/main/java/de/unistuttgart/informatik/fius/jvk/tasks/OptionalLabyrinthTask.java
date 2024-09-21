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
import de.unistuttgart.informatik.fius.jvk.provided.factories.BushFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;


/**
 * TODO: Description
 * 
 * @author Jannik Graef
 */
public class OptionalLabyrinthTask implements Task {
    
    @Override
    public void run(Simulation sim) {
        // TODO Auto-generated method stub
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        preparePlayingfield(pm);
        MySmartTotoro smartTotoro = new MySmartTotoro();
        pm.placeEntityAt(smartTotoro, new Position(0, 0));
        
        //your Code here
        //Löse das Labyrinth
    }
    
    private void preparePlayingfield(PlayfieldModifier pm) {
        pm.placeEntityAtEachPosition(new BushFactory(), new Rectangle(new Position(-1, -1), new Position(10, 5)));
        pm.placeEntityAt(new Bush(), new Position(1, 0));
        pm.placeEntityAt(new Bush(), new Position(3, 0));
        pm.placeEntityAt(new Bush(), new Position(5, 0));
        pm.placeEntityAt(new Bush(), new Position(3, 1));
        pm.placeEntityAt(new Bush(), new Position(7, 1));
        pm.placeEntityAt(new Bush(), new Position(8, 1));
        pm.placeEntityAt(new Bush(), new Position(0, 2));
        pm.placeEntityAt(new Bush(), new Position(1, 2));
        pm.placeEntityAt(new Bush(), new Position(5, 2));
        pm.placeEntityAt(new Bush(), new Position(6, 2));
        pm.placeEntityAt(new Bush(), new Position(7, 2));
        pm.placeEntityAt(new Bush(), new Position(1, 3));
        pm.placeEntityAt(new Bush(), new Position(3, 3));
        pm.placeEntityAt(new Bush(), new Position(5, 3));
        pm.placeEntityAt(new Bush(), new Position(7, 3));
        pm.placeEntityAt(new Bush(), new Position(8, 3));
        pm.placeEntityAt(new Bush(), new Position(9, 3));
        pm.placeEntityAt(new Bush(), new Position(3, 4));
        pm.placeEntityAt(new Home_Tree(), new Position(8, 2));
    }
    
}
