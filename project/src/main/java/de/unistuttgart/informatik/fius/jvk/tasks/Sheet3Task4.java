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


/**
 * TODO: Description
 * 
 * @author Jannik
 */
public class Sheet3Task4 implements Task {
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Totoro totoro = new Totoro();
        totoro.setNutsInPocket(12);
        pm.placeEntityAt(totoro, new Position(0, 0));

        //write code here
        
    }
    
    
    private void walkOneStep(Totoro totoro) {
        //write the Code for b) here
    }

    //write the code for c and d here
    
}
