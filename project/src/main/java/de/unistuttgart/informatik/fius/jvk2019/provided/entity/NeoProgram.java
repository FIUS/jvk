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

/**
 * A program for neo
 * 
 * @author Tim Neumann
 */
public abstract class NeoProgram extends TypedProgram<Neo> {
    
    /**
     * Create a new NeoProgram.
     */
    public NeoProgram() {
        super(Neo.class);
    }
}
