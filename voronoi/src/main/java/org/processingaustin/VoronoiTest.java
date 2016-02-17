package org.processingaustin;

import megamu.mesh.MPolygon;
import megamu.mesh.Voronoi;
import processing.core.PApplet;

public class VoronoiTest extends PApplet {
	private int WIDTH = 1024, HEIGHT = 768;
	private Voronoi voronoi;
	private static final int INITIAL_POINTS = 100;
	private MPolygon[] polygons;
	private float[][] points;
	private float[] speed;
	private int[] colors;
	private int baseColor;
	private static final float MAX_SPEED = 5;
	private static final float MIN_SPEED = 2;

	private void initPoints(int nPoints) {
		points = new float[nPoints][2];
		speed = new float[nPoints];
		colors = new int[nPoints];
		for(int i = 0; i < nPoints; ++i) {
			points[i][0] = random(1)*width;
			points[i][1] = random(1)*height;
			speed[i] = random(MIN_SPEED, MAX_SPEED);
			colors[i] = color(baseColor, 255, random(200, 255));
		}
	}

	public void settings() {
		size(WIDTH, HEIGHT);
	}

	public void setup() {
		background(255);
		colorMode(HSB);
		baseColor = 112;
		initPoints(INITIAL_POINTS);
	}

	void updatePoints() {
		for(int i = 0; i < points.length; ++i) {
			points[i][0] -= speed[i];
			if(points[i][0] < 0) {
				points[i][0] = width;
				points[i][1] = random(1) * height;
				speed[i] = random(MIN_SPEED, MAX_SPEED);
			}
		}
	}
	
	public void draw() {
		updatePoints();
		background(255);

		voronoi = new Voronoi(points);
		polygons = voronoi.getRegions();

		for(int i = 0; i < polygons.length; ++i) {
			MPolygon polygon = polygons[i];
			fill(colors[i]);
			polygon.draw(this);
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "org.processingaustin.VoronoiTest" });
	}
}
