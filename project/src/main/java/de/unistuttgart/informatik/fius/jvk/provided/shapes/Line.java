package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A finite length line shape that is always parallel to the coordinate axes.
 */
public class Line implements Shape {

    private List<Position> points;

    /**
     * Create a new line by specifying start and end position of the line.
     *
     * @param start the start of the line
     * @param end the end of the line; may not differ from the line start in both x and y direction
     */
    public Line(Position start, Position end) {
        Integer deltaX = start.getX() - end.getX();
        Integer deltaY = start.getY() - end.getY();

        if (deltaX != 0 && deltaY != 0) {
            throw new IllegalArgumentException("Only lines parallel to the grid are supported.");
        }

        List<Position> points = new ArrayList<>();
        points.add(start);

        Position currentPos = start;
        while (deltaX != 0 || deltaY != 0) {
            if (deltaX > 0) {
                currentPos = new Position(currentPos.getX() - 1, currentPos.getY());
                points.add(currentPos);
                deltaX -= 1;
            }
            if (deltaX < 0) {
                currentPos = new Position(currentPos.getX() + 1, currentPos.getY());
                points.add(currentPos);
                deltaX += 1;
            }
            if (deltaY > 0) {
                currentPos = new Position(currentPos.getX(), currentPos.getY() - 1);
                points.add(currentPos);
                deltaY -= 1;
            }
            if (deltaY < 0) {
                currentPos = new Position(currentPos.getX(), currentPos.getY() + 1);
                points.add(currentPos);
                deltaY += 1;
            }
        }

        this.points = points;
    }

    @Override
    public Iterator<Position> iterator() {
        return this.points.iterator();
    }

}