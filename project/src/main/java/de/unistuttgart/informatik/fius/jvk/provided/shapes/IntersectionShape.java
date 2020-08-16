package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A shape combining two shapes with the intersection operator of sets.
 */
public class IntersectionShape implements Shape {

    private Set<Position> points;

    /**
     * Create a new IntersectionShape.
     *
     * @param a the first shape
     * @param b the second shape
     */
    public IntersectionShape(Shape a, Shape b) {
        Set<Position> pointsA = new HashSet<>();

        for (Position pos : a) {
            pointsA.add(pos);
        }

        Set<Position> pointsB = new HashSet<>();

        for (Position pos : b) {
            pointsB.add(pos);
        }

        pointsA.retainAll(pointsB);

        this.points = pointsA;
    }

    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }

}