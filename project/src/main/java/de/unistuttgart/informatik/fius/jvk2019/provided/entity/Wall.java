/*
 * ICGE-Example-Mario
 *
 * TODO: Project Beschreibung
 *
 * @author Tim Neumann
 * @version 1.0.0
 *
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.SolidEntity;
import de.unistuttgart.informatik.fius.jvk2019.Texture;

/**
 * The wall entity
 * 
 * @author Tim Neumann
 */
public class Wall extends BasicEntity implements SolidEntity {

    @Override
    protected String getTextureHandle() {
        return Texture.WALL.getHandle();
    }

    @Override
    protected int getZPosition() {
        return 0;
    }

}
