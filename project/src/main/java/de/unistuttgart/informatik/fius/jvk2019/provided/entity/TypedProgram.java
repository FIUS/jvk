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

import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgram;


/**
 * A generic class to create programs that require a specific entity type.
 * 
 * @author Fabian Bühler
 */
public abstract class TypedProgram<T> implements EntityProgram {
    
    private final Class<? extends T> PROGRAM_ENTITY_TYPE;
    
    public TypedProgram(Class<T> entityType) {
        this.PROGRAM_ENTITY_TYPE = entityType;
    }
    
    /**
     * Run this program on the given greedy entity.
     * 
     * @param entity
     *     The greedy entity to run this program on
     */
    public abstract void run(T entity);
    
    @SuppressWarnings("unchecked")
    @Override
    public void run(final Entity entity) {
        if (this.PROGRAM_ENTITY_TYPE.isAssignableFrom(entity.getClass())) {
            this.run((T) entity);
        } else throw new IllegalArgumentException("Cannot run on that entity.");
    }
    
    @Override
    public boolean canRunOn(final Entity entity) {
        if (this.PROGRAM_ENTITY_TYPE.isAssignableFrom(entity.getClass())) return true;
        return false;
    }
}
