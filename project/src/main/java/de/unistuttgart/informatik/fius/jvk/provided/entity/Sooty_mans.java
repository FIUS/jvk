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
 * Class for sooty_mans (Rußmännchen)
 *
 * @author Sara Galle
 */
public class Sooty_mans extends Totoro {

    /**
     * changes the appearance of the player in the game from Neo to Morpheus
     */
    @Override
    protected String getTextureHandle() {
        return Texture.SOOTY_MANS.getHandle();
    }
    
    /**
     * TODO: Implement the operations of sooty_mans here
     * 
     */
    
}
