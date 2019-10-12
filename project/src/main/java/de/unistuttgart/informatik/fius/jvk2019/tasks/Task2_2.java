/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.tasks;

import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;

/**
 * 
 * Task for Sheet 2, Task 1 2019
 * @author Tim-Julian Ehret, Stefan Zindl
 */
public abstract class Task2_2 extends TaskWithHelperFunctions {
        
    /**
     * Spawns a cage with a coin in it.
     */
    @Override
    public void prepare(Simulation sim) {        
        super.prepare(sim);
        this.generateCage(6, 1);      
    }
    
    @Override
    public final boolean verify() {        
        //check if positon 0,0 to 0,5 contain coins.
        for (int i = 0; i < 6; i++) {
            List<Entity> e = this.sim.getPlayfield().getEntitiesAt(new Position(0,i));
            if(e.stream().noneMatch(item -> item.getClass() == Coin.class)) return false;            
        }        
        return true;
    }
}
