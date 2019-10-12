/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.jvk2019.Texture;


/**
 * Basic coin class.
 * 
 * @author Fabian Bühler
 */
public class Coin extends BasicEntity implements CollectableEntity {
    
    @Override
    protected String getTextureHandle() {
        return Texture.COIN.getHandle();
    }
    
    @Override
    protected int getZPosition() {
        return 0;
    }
}
