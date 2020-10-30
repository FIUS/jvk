package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A shape created by filling out an existing shape.
 */
public class FilledShape implements Shape {

    private Set<Position> points;

    /**
     * Create a new shape by filling out the given shape.
     *
     * @param shape the input shape
     */
    public FilledShape(Shape shape) {
        Set<Position> points = new HashSet<>();

        points.addAll(shape);

        if (points.isEmpty()) {
            this.points = points;
            return;
        }

        // calc bounding box...
        Integer x1 = Integer.MAX_VALUE;
        Integer x2 = Integer.MIN_VALUE;
        Integer y1 = Integer.MAX_VALUE;
        Integer y2 = Integer.MIN_VALUE;
        for (Position pos : points) {
            if (pos.getX() < x1) {
                x1 = pos.getX();
            }
            if (pos.getX() > x2) {
                x2 = pos.getX();
            }
            if (pos.getY() < y1) {
                y1 = pos.getY();
            }
            if (pos.getY() > y2) {
                y2 = pos.getY();
            }
        }

        Set<Position> outside = new HashSet<>();
        for (Integer y = y1; y <= y2; y++) {
            outside.add(new Position(x1-1, y));
            outside.add(new Position(x2+1, y));
        }
        for (Integer x = x1; x <= x2; x++) {
            outside.add(new Position(x, y1-1));
            outside.add(new Position(x, y2+1));
        }

        Set<Position> visited = new HashSet<>();
        Stack<Position> stack = new Stack<>();

        // flood fill outside
        stack.push(new Position(x1-1, y1-1));
        while (!stack.isEmpty()) {
            Position current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            if (current.getX() < x1 - 1) continue;
            if (current.getX() > x2 + 1) continue;
            if (current.getY() < y1 - 1) continue;
            if (current.getY() > y2 + 1) continue;
            if (!points.contains(current)) {
                outside.add(current);

                stack.push(new Position(current.getX(), current.getY()-1));
                stack.push(new Position(current.getX()-1, current.getY()));
                stack.push(new Position(current.getX(), current.getY()+1));
                stack.push(new Position(current.getX()+1, current.getY()));
            }
        }

        
        // flood fill inside
        visited.clear();
        for (Position pos : points) {
            stack.push(pos);
        }
        while (!stack.isEmpty()) {
            Position current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            if (!outside.contains(current)) {
                points.add(current);

                stack.push(new Position(current.getX(), current.getY()-1));
                stack.push(new Position(current.getX()-1, current.getY()));
                stack.push(new Position(current.getX(), current.getY()+1));
                stack.push(new Position(current.getX()+1, current.getY()));
            }
        }

        this.points = points;
    }

    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }

}
