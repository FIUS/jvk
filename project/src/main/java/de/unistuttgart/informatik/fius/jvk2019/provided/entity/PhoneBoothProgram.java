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
 * A program for PhoneBooth
 * 
 * @author Fabian Bühler
 */
public abstract class PhoneBoothProgram extends TypedProgram<PhoneBooth> {
    
    /**
     * Create a new PhoneBoothProgram.
     */
    public PhoneBoothProgram() {
        super(PhoneBooth.class);
    }
}
