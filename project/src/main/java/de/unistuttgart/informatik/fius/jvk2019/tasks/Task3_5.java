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

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;

/**
 * TODO: Task for ex 5 in ws 3
 * @author paulesn
 */
public abstract class Task3_5 extends TaskWithHelperFunctions {
    
    protected Neo neo;
    
    public void prepare(Simulation sim) {
        this.neo = new Neo();
        sim.getPlayfield().addEntity(new Position(0,0), this.neo);
        for(int i = 0; i<10;++i) {
            this.neo.collect(new Coin());
        }
    }
    
    @Override
    public boolean verify() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
