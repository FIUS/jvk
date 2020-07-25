/*
 * This source file is part of the FIUS JVK 2020 project.
 * For more information see github.com/FIUS/JVK
 *
 * Copyright (c) 2020 the FIUS JVK 2020 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.provided.entity;

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
