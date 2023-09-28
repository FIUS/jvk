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

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.Texture;


/**
 * Class for Morpheus.
 *
 * @author Sara Galle
 */
public class Morpheus extends Neo {

    /**
     * changes the appearance of the player in the game from Neo to Morpheus
     */
    @Override
    protected String getTextureHandle() {
        return Texture.MORPHEUS.getHandle(this.getLookingDirection());
    }
    
    /**
     * TODO: Implement the operations of Morpheus here
     * 
     */
    
}
