package de.unistuttgart.informatik.fius.jvk.provided.factories;

import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Nut;

import java.util.function.Supplier;

/**
 * Factory to create new {@link Nut} Objects for the {@link PlayfieldModifier}.
 */
public class NutFactory implements Supplier<Nut> {

	@Override
	public Nut get() {
		return new Nut();
	}


}
