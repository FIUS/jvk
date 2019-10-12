/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task1;


/**
 * The example solution for Task1
 *
 * @author Fabian Bühler
 */
public class Solution1 extends Task1 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new Neo();
        this.spawnEntity(this.player, 0, 0);
    }
    
    @Override
    public void solve() {
        // do AB1 task 6 here
        // Move Neo to the field with the telephone.
        // you only need to use the Operations this.player.move(); and this.player.turnClockWise(); to complete the task
        this.player.move();
    }
    
}
