package org.processingaustin;

public class VariantValue {
	private final int base;
	private final int variation;

	public VariantValue(int base, int variation) {
		this.base = base;
		this.variation = variation;
	}

	public int gen() {
		double value = base+Math.random()*variation;
		return (int)Math.min(255, Math.max(0, value));
	}
}
