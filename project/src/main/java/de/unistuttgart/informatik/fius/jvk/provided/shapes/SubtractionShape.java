package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A shape combining two shapes by subtracting one from the other.
 */
public class SubtractionShape implements Shape {

    private Set<Position> points;

    /**
     * Create a new Subtraction shape by subtracting shape b from shape a (a-b).
     * @param a the base shape
     * @param b the shape to substract from the base shape
     */
    public SubtractionShape(Shape a, Shape b) {
        Set<Position> pointsA = new HashSet<>();

        for (Position pos : a) {
            pointsA.add(pos);
        }

        Set<Position> pointsB = new HashSet<>();

        for (Position pos : b) {
            pointsB.add(pos);
        }

        pointsA.removeAll(pointsB);

        this.points = pointsA;
    }

    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }

}