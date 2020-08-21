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

import de.unistuttgart.informatik.fius.jvk.Texture;


/**
 * Class for Morpheus.
 *
 * @author Fabian Bühler
 */
public class Morpheus extends Human {

    @Override
    protected String getTextureHandle() {
        return Texture.MORPHEUS.getHandle(this.getLookingDirection());
    }

}
