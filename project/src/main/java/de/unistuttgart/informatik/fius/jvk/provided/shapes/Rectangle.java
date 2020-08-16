package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.Iterator;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A hollow rectangle shape.
 */
public class Rectangle implements Shape {

    private Shape rect;

    /**
     * Create a rectangle by specifying two corners of the rectangle.
     *
     * @param cornerA the first corner of the rectangle
     * @param cornerB the second corner of the rectangle; must not be the same as cornerA
     */
    public Rectangle(Position cornerA, Position cornerB) {
        if (cornerA.equals(cornerB)) throw new IllegalArgumentException("The two corners of a rectangle cannot be the same position!");
        this.rect = new UnionShape(
            new Line(cornerA, new Position(cornerA.getX(), cornerB.getY())),
            new Line(cornerA, new Position(cornerB.getX(), cornerA.getY())),
            new Line(new Position(cornerA.getX(), cornerB.getY()), cornerB),
            new Line(new Position(cornerB.getX(), cornerA.getY()), cornerB)
        );
    }

    @Override
    public Iterator<Position> iterator() {
        return this.rect.iterator();
    }
}