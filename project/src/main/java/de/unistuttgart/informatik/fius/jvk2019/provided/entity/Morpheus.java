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

import de.unistuttgart.informatik.fius.jvk2019.Texture;


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
