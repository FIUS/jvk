package de.unistuttgart.informatik.fius.jvk.provided.factories;

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;

/**
 * Factory to create new {@link Coin} Objects for the {@link PlayfieldModifier}.
 */
public class CoinFactory implements Supplier<Coin> {

	@Override
	public Coin get() {
		return new Coin();
	}


}
