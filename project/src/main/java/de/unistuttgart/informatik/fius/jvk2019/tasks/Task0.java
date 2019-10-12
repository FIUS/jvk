/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.tasks;

import java.util.stream.Stream;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * An example task
 * 
 * @author Fabian Bühler
 */
public abstract class Task0 extends TaskWithHelperFunctions {
    
    /**
     * The walking neo
     */
    protected Neo player;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.generateCage(5, 1);
    }
    
    @Override
    public boolean verify() {
        // check playfield
        final Playfield field = this.sim.getPlayfield();
        boolean playFieldOK = true;
        playFieldOK = playFieldOK && (field.getEntitiesAt(new Position(0, 0)).size() == 0);
        playFieldOK = playFieldOK && (field.getEntitiesAt(new Position(1, 0)).size() == 0);
        playFieldOK = playFieldOK && (field.getEntitiesAt(new Position(2, 0)).size() == 0);
        playFieldOK = playFieldOK && (field.getEntitiesAt(new Position(3, 0)).size() == 0);
        playFieldOK = playFieldOK && (field.getEntitiesAt(new Position(4, 0)).size() == 1);
        if (!playFieldOK) {
            System.out.println("Only the player should remain on the field to finish this task!");
            return false;
        }
        // check player position
        final Position pos = this.player.getPosition();
        return pos.getX() == 4 && pos.getY() == 0 && !this.player.canMove();
    }
    
}
