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
import de.unistuttgart.informatik.fius.icge.simulation.entity.SolidEntity;
import de.unistuttgart.informatik.fius.jvk.Texture;

/**
 * The bush entity
 *
 * @author Sara Galle
 */
public class Bush extends BasicEntity implements SolidEntity {

    @Override
    protected String getTextureHandle() {
        return Texture.BUSH.getHandle();
    }

    @Override
    protected int getZPosition() {
        return 0;
    }

}
