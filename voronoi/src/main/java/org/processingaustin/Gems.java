package org.processingaustin;

import processing.core.PApplet;

public class Gems extends PApplet {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private Diamond diamond;

	public void settings() {
		size(WIDTH, HEIGHT, P2D);
	}

	public void setup() {
		background(255);
		diamond = new Diamond(this);
	}

	public void draw() {
		background(255);
		translate(width/2, height/2);
		scale(-10);
		diamond.draw();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "org.processingaustin.Gems" });
	}
}
