package org.processingaustin;

import processing.core.*;
import megamu.mesh.Voronoi;
import megamu.mesh.MPolygon;
import java.util.Random;

public class VoronoiTest extends PApplet {
	private int WIDTH = 1920, HEIGHT = 1080;
	private Voronoi voronoi;
	private static final int INITIAL_POINTS = 10;
	private Random random = new Random();
	private MPolygon[] polygons;
	private float[][] points;

	private float[][] randomPoints(int nPoints) {
		float [][] points = new float[nPoints][2];

		for(int i = 0; i < nPoints; ++i) {
			points[i][0] = random.nextFloat()*width;
			points[i][1] = random.nextFloat()*height;
			println(points[i][0] + ", " + points[i][1]);
		}

		return points;
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
		points = randomPoints(INITIAL_POINTS);
//		voronoi = new Voronoi(points);
//		polygons = voronoi.getRegions();
	}

	public void draw() {
		background(255);

//		for(MPolygon polygon : polygons) {
//			polygon.draw(this);
//		}
		
		drawPoints(points);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "org.processingaustin.VoronoiTest" });
	}
}
