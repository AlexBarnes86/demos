package org.processingaustin;

import processing.core.*;
import java.util.Random;

public class Rust extends PApplet {
	//TODO: Import Rust Distribution Map
	//TODO: Import Rust Probability Map
	
	private static final int END_FRAME = 100;
	private int WIDTH = 200, HEIGHT = 200;
	boolean [][] rustMap = new boolean[WIDTH][HEIGHT];
	double [][] rustMapAge = new double[WIDTH][HEIGHT];
	Random random = new Random();

	public void settings() {
		size(WIDTH, HEIGHT);
	}

	public void initRustMap() {
		for(int r = 0; r < height; ++r) {
			for(int c = 0; c < width; ++c) {
				if (frameCount == 0) {
					rustMap[r][c] = random.nextDouble() > 0.6 ? true : false;
					rustMapAge[r][c] = 0;
				}
			}
		}
	}

	public void setup() {
		background(255);
		initRustMap();
	}

	public void draw() {
		background(255);
		loadPixels();
		for(int r = 0; r < height; ++r) {
			for(int c = 0; c < width; ++c) {
				if(rustMap[r][c]) {
					pixels[r*width+c] = color(200, 150, 0);// color(255, 255, 255);
				}
			}
		}
		updatePixels();
		if(frameCount <= END_FRAME) {
			rusterizeStep();
		}
	}

	private int countNeighbors(int row, int col) {
		int neighbors = 0;
		int startRow = row - 1 < 0 ? 0 : row - 1;
		int endRow = row+1 > height - 1 ? height - 1 : row + 1;
		int startCol = col - 1 < 0 ? 0 : col - 1;
		int endCol = col + 1 > width - 1 ? width - 1 : col + 1;

		for(int r = startRow; r < endRow; ++r) {
			for(int c = startCol; c < endCol; ++c) {
				//Dont count self
				if(r == row && c == col) {
					continue;
				}
				if(rustMap[r][c]) {
					neighbors++;
				}
			}
		}

		return neighbors;
	}

	public void rusterizeStep() {
		double ageIncrement = (1.0 / (frameCount+1)) * 5.0;
		double probabilityA = 0.8;
		double probabilityB = (probabilityA - ((1.0 - probabilityA) / 2.0));

		boolean [][] nextMap = new boolean[width][height];
		double [][] nextMapAge = new double[width][height];

		for(int r = 0; r < height; ++r) {
			for(int c = 0; c < width; ++c) {
				if (rustMap[r][c]) {
					nextMap[r][c] = rustMap[r][c];
					nextMapAge[r][c] = rustMapAge[r][c] + ageIncrement;
				}
				else {
					int neighbors = countNeighbors(r, c);

					if (!rustMap[r][c] && neighbors == 3 && random.nextDouble() >= probabilityA) {
						nextMap[r][c] = true;
					}
					else if (!rustMap[r][c] && neighbors == 4 && random.nextDouble() >= probabilityB) {
						nextMap[r][c] = true;
					}
				}

				if(nextMapAge[r][c] > 1) {
					nextMapAge[r][c] = 1;
				}
			}
		}

		rustMap = nextMap;
		rustMapAge = nextMapAge;
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "org.processingaustin.Rust" });
	}
}
