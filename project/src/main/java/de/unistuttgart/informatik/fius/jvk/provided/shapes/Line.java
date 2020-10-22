package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import de.unistuttgart.informatik.fius.icge.simulation.Position;


/**
 * A finite length line shape.
 */
public class Line implements Shape {
    
    private List<Position> points;
    
    /**
     * Create a new line by specifying start and end position of the line.
     *
     * @param start
     *     the start of the line
     * @param end
     *     the end of the line; may not differ from the line start in both x and y direction
     */
    public Line(Position start, Position end) {
        Integer deltaX = end.getX() - start.getX();
        Integer deltaY = end.getY() - start.getY();
        Float slope; //slope needs to have digits after the . ,so Float is used
        
        List<Position> points = new ArrayList<>();
        
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            slope =  deltaY.floatValue() / deltaX.floatValue();
            for (Integer x = start.getX(); (deltaX > 0) ? x <= end.getX() : x >= end.getX(); x += ((deltaX > 0 ? 1 : -1))) {
                Integer y = Math.round(x.floatValue() * slope + start.getX());
                points.add(new Position(x, y));
            }
        }
        else{
            slope =  deltaX.floatValue() / deltaY.floatValue();
            for(Integer y = start.getY(); (deltaY > 0) ? y <= end.getY() : y >= end.getY(); y += ((deltaY > 0 ? 1 : -1))){
                Integer x = Math.round(y.floatValue() * slope + start.getY());
                points.add(new Position(x, y));
            }
        }
        this.points = points;
    }
    
    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }
    
}
