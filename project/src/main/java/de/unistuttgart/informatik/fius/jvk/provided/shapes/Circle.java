package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import de.unistuttgart.informatik.fius.icge.simulation.Position;


/**
 * A finite length line shape.
 */
public class Circle implements Shape {
    
    private List<Position> points;
    
    /**
     * Create a new line by specifying start and end position of the line.
     *
     * @param start
     *     the start of the line
     * @param end
     *     the end of the line; may not differ from the line start in both x and y direction
     */
    public Circle(Position middle, Integer radius) {
        
        
        List<Position> points = new ArrayList<>();
        List<Position> pointsAdd = new ArrayList<>();
        for(Integer x = 0; x < radius;x++){
            Double y = Math.sin(Math.acos(x.doubleValue()/radius))*radius;
            points.add(new Position(x,(int)Math.round(y)));
        }
        for(Integer y = 0; y < radius;y++){
            Double x = Math.cos(Math.asin(y.doubleValue()/radius))*radius;
            Position position = new Position((int)Math.round(x),y);
            if(!points.contains(position)){
                points.add(position);
            }
        }
        points.forEach(p -> pointsAdd.add(new Position(p.getX(), -1*p.getY())));
        pointsAdd.forEach(p -> {if(!points.contains(p))points.add(p);} );
        pointsAdd.clear();
        points.forEach(p -> pointsAdd.add(new Position(-1*p.getX(), p.getY())));
        pointsAdd.forEach(p -> {if(!points.contains(p))points.add(p);} );
        this.points=points;
    }
    
    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }
    
}
