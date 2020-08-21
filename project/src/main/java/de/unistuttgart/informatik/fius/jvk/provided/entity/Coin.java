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

import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.jvk.Texture;


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
