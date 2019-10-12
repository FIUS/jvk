/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;

/**
 * a set of tools for an easy field creation
 * @author paulesn
 */
public class SimulationUtilities {
    
    /**
     * creates Wall Entities so that they form a rectangle surrounding an area of the size height*width
     * @param sim the Simulation you want the rectangle in
     * @param height the height of the rectangle (&gt; 0)
     * @param width the width of the rectangle (&gt; 0)
     * @param x the x coordinate of the most upper left Wall entity
     * @param y the y coordinate of the most upper left Wall entity
     */
    public static void createRectangleWall(Simulation sim, int height, int width, int x, int y) {
        //test if values are positive as they should be
        if(height<0 || width<0) {
            return;
        }
        //changes so that the values fit the inner area of the rectangle
        height = height+1;
        width = width+1;
        
        sim.getPlayfield().addEntity(new Position(x,y), new Wall());
        for(int i=x+1; i<=x+width;i++) {
            sim.getPlayfield().addEntity(new Position(i,y), new Wall());
        }
        for(int i=y+1; i<=y+height;i++) {
            sim.getPlayfield().addEntity(new Position(x,i), new Wall());
        }
        for(int i=x+1; i<=x+width;i++) {
            sim.getPlayfield().addEntity(new Position(i,y+height), new Wall());
        }
        for(int i=y+1; i<=x+height-1;i++) {
            sim.getPlayfield().addEntity(new Position(x+width,i), new Wall());
        }
    }
    
}
