package org.processingaustin;

import processing.core.PApplet;

public class ColorVariance {
	private final VariantValue h;
	private final VariantValue s;
	private final VariantValue b;

	public ColorVariance(VariantValue h, VariantValue s, VariantValue b) {
		this.h = h;
		this.s = s;
		this.b = b;
	}

	public ColorVariance(int h, int hv, int s, int sv, int b, int bv) {
		this.h = new VariantValue(h, hv);
		this.s = new VariantValue(s, sv);
		this.b = new VariantValue(b, bv);
	}

	public int generateColor(PApplet applet) {
		applet.colorMode(PApplet.HSB, 255);
		return applet.color(h.gen(), s.gen(), b.gen());
	}
}
