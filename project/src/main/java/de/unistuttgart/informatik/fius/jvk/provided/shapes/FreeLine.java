/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;


/**
 * A finite length line shape that can be parallel to the coordinate axes or diagonal
 */
public class FreeLine implements Shape {
    
    private List<Position> points;
    
    /**
     * Create a new line by specifying start and end position of the line.
     * 
     * @param start
     *     the start of the line
     * @param end
     *     the end of the line; may differ from the line start in both x and y direction
     */
    public FreeLine(Position start, Position end) {
        List<Position> points = new ArrayList<>();
        Integer deltaX = end.getX() - start.getX();
        Integer deltaY = end.getY() - start.getY();
        Integer xDir = (deltaX == 0) ? 0 : (deltaX > 0) ? 1 : -1;
        Integer yDir = (deltaY == 0) ? 0 : (deltaY > 0) ? 1 : -1;
        
        points.add(start);
        Position currentPos = start;
        
        if (deltaX == 0) { // vertical line
            while (deltaY != 0) {
                currentPos = new Position(currentPos.getX(), currentPos.getY() + yDir);
                points.add(currentPos);
                deltaY -= yDir;
            }
        } else if (deltaY == 0) { // horizontal line
            while (deltaX != 0) {
                currentPos = new Position(currentPos.getX() + xDir, currentPos.getY());
                points.add(currentPos);
                deltaX -= xDir;
            }
        } else { // diagonal line
            Float grade = Math.abs(deltaY / deltaX.floatValue());
            if (grade < 1.f) {
                grade = Math.signum(deltaY) * grade;
                Float currentY = (float) start.getY();
                while (deltaX != 0) {
                    currentY += grade;
                    currentPos = new Position(currentPos.getX() + xDir, Math.round(currentY));
                    points.add(currentPos);
                    deltaX -= xDir;
                }
            } else { // grade >= 1.0f
                grade = Math.signum(deltaX) * Math.abs(deltaX / deltaY.floatValue());
                Float currentX = (float) start.getX();
                while (deltaY != 0) {
                    currentX += grade;
                    currentPos = new Position(Math.round(currentX), currentPos.getY() + yDir);
                    points.add(currentPos);
                    deltaY -= yDir;
                }
            }
        }
        this.points = points;
    }
    
    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }
}