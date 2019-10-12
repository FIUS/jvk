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

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.entity.*;
import de.unistuttgart.informatik.fius.jvk2019.Texture;


/**
 * A block to leave the Matrix
 *
 * @author paulesn
 */
public class PhoneBooth extends BasicEntity implements SolidEntity {
    
    /**
     * If the phoneBooth is not reachable it is solid. (default: {@code true})
     * <p>
     * Set to true if all requirements for this phoneBooth are met. If this attribute is false the phonebooth will be
     * solid.
     */
    private boolean isReachable = true;
    
    /**
     * A checker function that returns true if and only if all requirements to access this phonebooth are met.
     * <p>
     * The requirements checker decision only will be used if isReachable is true!
     */
    private Supplier<Boolean> requirementsChecker = null;
    
    /**
     * Set the status of the requirements for this phoneBooth to fulfilled.
     */
    public void setRequirementsAsFulfilled() {
        this.isReachable = true;
    }
    
    /**
     * Set the status of the requirements for this phoneBooth to unfulfilled.
     */
    public void setRequirementsNotFulfilled() {
        this.isReachable = false;
    }
    
    /**
     * Set a checker function that returns true if and only if all requirements to access this phonebooth are met.
     *
     * @param requirementsChecker
     *     the requirementsChecker to set
     */
    public void setRequirementsChecker(Supplier<Boolean> requirementsChecker) {
        this.requirementsChecker = requirementsChecker;
    }
    
    /**
     * @return the requirementsChecker
     */
    public Supplier<Boolean> getRequirementsChecker() {
        return this.requirementsChecker;
    }
    
    @Override
    public boolean isCurrentlySolid() {
        if (!this.isReachable) return true;
        // PhoneBooth is reachable, test requirementsChecker
        if (this.requirementsChecker == null) return false;
        return !this.requirementsChecker.get();
    }
    
    @Override
    protected String getTextureHandle() {
        if (this.isCurrentlySolid()) return Texture.PHONEBOOTH.getHandle();
        return Texture.PHONEBOOTH_RINGING.getHandle();
    }
    
    @Override
    protected int getZPosition() {
        return 1;
    }
    
}
