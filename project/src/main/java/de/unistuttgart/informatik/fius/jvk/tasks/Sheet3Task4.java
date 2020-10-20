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

import java.util.Iterator;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;


/**
 * TODO: Description
 * 
 * @author Jannik
 */
public class Sheet3Task3 implements Task {
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        neo.setCoinsInWallet(12);
        pm.placeEntityAt(neo, new Position(0, 0));

        //write code here
        
    }
    
    
    private void walkOneStep(Neo neo) {
        //write the Code for b) here
    }

    //write the code for c and d here
    
}
