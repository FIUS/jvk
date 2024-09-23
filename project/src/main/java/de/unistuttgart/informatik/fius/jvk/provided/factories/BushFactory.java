package de.unistuttgart.informatik.fius.jvk.provided.factories;

import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Bush;

import java.util.function.Supplier;

/**
 * Factory to create new {@link Bush} Objects for the {@link PlayfieldModifier}.
 */
public class BushFactory implements Supplier<Bush> {

	@Override
	public Bush get() {
		return new Bush();
	}

}
