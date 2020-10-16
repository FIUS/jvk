package de.unistuttgart.informatik.fius.jvk.provided.factories;

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.Color;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Pill;

/**
 * Factory to create new {@link Pill} Objects for the {@link PlayfieldModifier}.
 */
public class PillFactory implements Supplier<Pill> {

	private final Color color;

	/**
	 * Create a new PillFactory that can create new {@link Pill} Objects with the given color.
	 *
	 * @param color
	 *         The color of the new {@link Pill} Objects
	 */
	public PillFactory(final Color color) {
		this.color = color;
	}

	@Override
	public Pill get() {
		return new Pill(this.color);
	}

}
