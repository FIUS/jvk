package de.unistuttgart.informatik.fius.jvk.provided.entity;

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.entity.*;
import de.unistuttgart.informatik.fius.jvk.Texture;

/**
 * an object where that the player is able to step on, but not able to pick up
 *
 * @author Sara galle
 */
public class Home_Tree extends BasicEntity implements SolidEntity {
    @Override
    public boolean isCurrentlySolid() {
        return false;
    }

    @Override
    protected String getTextureHandle() {
        return Texture.HOME_TREE.getHandle();
    }

    @Override
    protected int getZPosition() {
        return 1;
    }
}
