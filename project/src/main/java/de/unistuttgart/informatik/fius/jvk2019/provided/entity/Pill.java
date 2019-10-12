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

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.ui.Drawable;
import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;

/**
 * TODO: Description
 * @author Tim-Julian Ehret, paulesn
 */
public class Pill extends BasicEntity implements CollectableEntity{
    
    private Color color;
    
    /**
     * constructor for a new Pill
     * @param color the color of the Pill
     */
    public Pill(Color color) {
        this.color = color;
    }
      
    
    /**
     * @return the color of this pill
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    protected int getZPosition() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    protected String getTextureHandle() {
        if(color == Color.BLUE) {
            return Texture.BLUEPILL.getHandle();
        }
        if(color == Color.RED) {
            return Texture.REDPILL.getHandle();
        }
        return Texture.REDPILL.getHandle();
    }
    
}
