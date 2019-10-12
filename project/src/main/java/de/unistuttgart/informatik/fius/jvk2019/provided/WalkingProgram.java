/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided;

import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.NeoProgram;


/**
 * A program walking around neo a bit
 * 
 * @author Tim Neumann
 */
public class WalkingProgram extends NeoProgram {
    
    @Override
    public void run(final Neo neo) {
        while (true) {
            if (neo.canMove()) {
                neo.move();
            } else {
                neo.turnClockWise();
                neo.turnClockWise();
            }
        }
    }
    
}
