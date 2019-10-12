/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;


/**
 * Base class for all Humans.
 * 
 * @author Fabian Bühler
 */
public abstract class Human extends GreedyEntity {
    
    @Override
    protected int getZPosition() {
        return 10;
    }
    
}
