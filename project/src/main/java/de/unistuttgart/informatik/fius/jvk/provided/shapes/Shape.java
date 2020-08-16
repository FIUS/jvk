package de.unistuttgart.informatik.fius.jvk.provided.shapes;


import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;

/**
 * An interface for shapes that can be used with the {@link PlayfieldModifier} api.
 * <p>
 * A shape is a collection of {@link Position} objects thet represent filled in pixels of the shape.
 * A {@link Position} may be present multiple time to cont the number of times the pixel should be painted.
 */
public interface Shape extends Iterable<Position> {

}
