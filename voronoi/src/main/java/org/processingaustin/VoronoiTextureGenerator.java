package org.processingaustin;

import megamu.mesh.MPolygon;
import megamu.mesh.Voronoi;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class VoronoiTextureGenerator {
	public PImage generate(PApplet applet, int width, int height, float[][] points, int [] colors) {
		PGraphics pg = applet.createGraphics(width, height);
		Voronoi voronoi = new Voronoi(points);
		MPolygon [] polygons = voronoi.getRegions();
		pg.beginDraw();
		for(int i = 0; i < polygons.length; ++i) {
			pg.color(colors[i]);
			polygons[i].draw(pg);
		}
		pg.endDraw();
		return pg.get();
	}
}
