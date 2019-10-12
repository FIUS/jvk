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

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.SimulationUtilities;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;

/**
 * TODO: Description
 * @author paulesn
 */
public abstract class Task3_4 extends TaskWithHelperFunctions {
    
    private Neo neo;
    private Playfield field;
    
    public void prepare(Simulation sim) {
        
        this.neo = new Neo();
        this.field = sim.getPlayfield();
        this.field.addEntity(new Position(-3, -3), this.neo);
        
        SimulationUtilities.createRectangleWall(sim, 10, 10, -1, -1);
        
        this.field.addEntity(new Position(1, 0), new Pill(Color.RED));
        this.field.addEntity(new Position(2, 0), new Pill(Color.BLUE));
        this.field.addEntity(new Position(3, 0), new Pill(Color.RED));
        this.field.addEntity(new Position(4, 0), new Pill(Color.BLUE));
        this.field.addEntity(new Position(6, 0), new Pill(Color.BLUE));
        this.field.addEntity(new Position(7, 0), new Pill(Color.RED));
        this.field.addEntity(new Position(8, 0), new Pill(Color.RED));
        this.field.addEntity(new Position(9, 0), new Pill(Color.BLUE));
        
        this.field.addEntity(new Position(1, 1), new Pill(Color.RED));
        this.field.addEntity(new Position(2, 3), new Pill(Color.BLUE));
        this.field.addEntity(new Position(3, 2), new Pill(Color.RED));
        this.field.addEntity(new Position(4, 3), new Pill(Color.BLUE));
        this.field.addEntity(new Position(6, 3), new Pill(Color.BLUE));
        this.field.addEntity(new Position(7, 2), new Pill(Color.RED));
        this.field.addEntity(new Position(8, 4), new Pill(Color.RED));
        this.field.addEntity(new Position(9, 5), new Pill(Color.BLUE));
        this.field.addEntity(new Position(10, 10), new Pill(Color.BLUE));
        this.field.addEntity(new Position(1, 3), new Pill(Color.RED));
        this.field.addEntity(new Position(2, 5), new Pill(Color.BLUE));
        this.field.addEntity(new Position(3, 6), new Pill(Color.RED));
        this.field.addEntity(new Position(4, 6), new Pill(Color.BLUE));
        this.field.addEntity(new Position(6, 6), new Pill(Color.BLUE));
        this.field.addEntity(new Position(7, 7), new Pill(Color.RED));
        this.field.addEntity(new Position(0, 10), new Pill(Color.RED));
    }
    
    @Override
    public boolean verify() {
        Boolean flag = true;
        if(this.field.getEntitiesOfTypeAt(new Position(0,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(1,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(2,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(3,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(5,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(6,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(7,0), Pill.class,true) == null) {
            return false;
        }
        if(this.field.getEntitiesOfTypeAt(new Position(8,0), Pill.class,true) == null) {
            return false;
        }
        return true;
    }
    
}
