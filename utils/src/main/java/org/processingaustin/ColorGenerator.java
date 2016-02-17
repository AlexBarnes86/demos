package org.processingaustin;

import processing.core.PApplet;

public class ColorGenerator {
	public int[] generate(PApplet applet, int nColors, ColorVariance variance) {
		int [] colors = new int[nColors];
		for(int i = 0; i < nColors; ++i) {
			colors[i] = variance.generateColor(applet);
		}
		return colors;
	}
}
