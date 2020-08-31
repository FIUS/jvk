package de.unistuttgart.informatik.fius.jvk.provided.factories;

import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;

public class CoinFactory implements Supplier<Coin> {

	@Override
	public Coin get() {
		return new Coin();
	}


}
