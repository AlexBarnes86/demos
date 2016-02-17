package org.processingaustin;

import processing.core.PApplet;

public class DrawUtil {
	public static void drawPoints(PApplet applet, float[][] points) {
		for(int i = 0; i < points.length; ++i) {
			int x = (int)points[i][0];
			int y = (int)points[i][1];
			applet.rect(x-2, y-2, 4, 4);
		}
	}
}
