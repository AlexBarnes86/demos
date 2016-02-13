package org.processingaustin;

import java.util.Random;

import megamu.mesh.MPolygon;
import megamu.mesh.Voronoi;
import processing.core.PApplet;

public class VoronoiTest extends PApplet {
	private int WIDTH = 1024, HEIGHT = 768;
	private Voronoi voronoi;
	private static final int INITIAL_POINTS = 100;
	private Random random = new Random();
	private MPolygon[] polygons;
	private float[][] points;
	private float[] speed;
	private static final float MAX_SPEED = 5;
	private static final float MIN_SPEED = 2;

	private void initPoints(int nPoints) {
		points = new float[nPoints][2];
		speed = new float[nPoints];

		for(int i = 0; i < nPoints; ++i) {
			points[i][0] = random.nextFloat()*width;
			points[i][1] = random.nextFloat()*height;
			speed[i] = random.nextFloat()*(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
		}
	}
	
	private void drawPoints(float[][] points) {
		for(int i = 0; i < points.length; ++i) {
			int x = (int)points[i][0];
			int y = (int)points[i][1];
			rect(x-2, y-2, 4, 4);
		}
	}

	public void settings() {
		size(WIDTH, HEIGHT);
	}

	public void setup() {
		background(255);
		initPoints(INITIAL_POINTS);
	}

	void updatePoints() {
		for(int i = 0; i < points.length; ++i) {
			points[i][0] -= speed[i];
			if(points[i][0] < 0) {
				points[i][0] = width;
				points[i][1] = random.nextFloat() * height;
				speed[i] = random.nextFloat()*(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
			}
		}
	}

	public void draw() {
		updatePoints();
		background(255);

		voronoi = new Voronoi(points);
		polygons = voronoi.getRegions();

		for(MPolygon polygon : polygons) {
			polygon.draw(this);
		}
		
		//drawPoints(points);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "org.processingaustin.VoronoiTest" });
	}
}
