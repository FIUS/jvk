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
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task0;


/**
 * The example solution for Task0
 * 
 * @author Fabian Bühler
 */
public class Solution0_1 extends Task0 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new Neo();
        this.spawnEntity(this.player, 0, 0);
    }
    
    @Override
    public void solve() {
        // simple solve method directly calling the entity methods
        this.player.move();
        this.player.move();
        // do AB1 task 5 here
        this.player.move();
        this.player.move();
        if (this.player.canMove()) {
            this.player.move();
        }
    }
    
}
