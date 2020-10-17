package de.unistuttgart.informatik.fius.jvk.provided.factories;

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;

/**
 * Factory to create new {@link Wall} Objects for the {@link PlayfieldModifier}.
 */
public class WallFactory implements Supplier<Wall> {

	@Override
	public Wall get() {
		return new Wall();
	}

}
