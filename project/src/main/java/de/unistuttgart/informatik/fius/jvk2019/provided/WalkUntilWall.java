/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided;

import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.GreedyEntityProgram;


/**
 * A program that walks an entity until it hits a wall.
 * 
 * @author Fabian Bühler
 */
public class WalkUntilWall extends GreedyEntityProgram {
    
    @Override
    public void run(final GreedyEntity entity) {
        while (entity.canMove()) {
            entity.move();
        }
    }
    
}
