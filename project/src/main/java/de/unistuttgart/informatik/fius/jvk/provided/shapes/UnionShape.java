package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A shape combining multiple shapes with the union operator of sets.
 */
public class UnionShape implements Shape {

    private Set<Position> points;

    /**
     * Create a new union shape.
     *
     * @param shapes the input shapes
     */
    public UnionShape(Shape ...shapes) {
        Set<Position> points = new HashSet<>();

        for (Shape shape : shapes) {
            for (Position pos : shape) {
                points.add(pos);
            }
        }

        this.points = points;
    }

    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }

}