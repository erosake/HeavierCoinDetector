package eros.data;

import eros.data.interfaces.Weightable;

public class Coin implements Weightable {

	public static final Coin NONE = new Coin(0.0d);

	private final double weight;

	public Coin(double weight) {

		this.weight = weight;

	}

	@Override
	public double retrieveWeight() {

		return weight;

	}

	@Override
	public String toString() {

		return String.format("Coin [weight=%.2fg]", weight);

	}

}
