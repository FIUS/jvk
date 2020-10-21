package de.unistuttgart.informatik.fius.jvk.provided.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

/**
 * A single pixel shape.
 */
public class Pixel implements Shape {

    private List<Position> pixel;

    /**
     * Create a new pixel by specifying its position.
     *
     * @param pixel the position of the pixel
     */
    public Pixel(Position pixel) {
        this.pixel = new ArrayList<>();
        this.pixel.add(pixel);
    }

    @Override
    public Iterator<Position> iterator() {
        return this.pixel.iterator();
    }

}
