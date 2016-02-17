package org.processingaustin;

import megamu.mesh.MPolygon;
import processing.core.PApplet;
import processing.core.PImage;

//TODO: Move scene object definitions to some kind of data format file
public class Diamond {
	private PApplet applet; 
	private ColorGenerator colorGen;
	private PointGenerator pointGen;
	private VoronoiTextureGenerator voronoiGen;
	private PImage gemTexture;
	private ColorVariance variance;
	private MPolygon mesh;

	public Diamond(PApplet applet) {
		this.applet = applet;
		variance = new ColorVariance(250, 5, 250, 5, 250, 5);
		buildMesh();
		generateTexture();
	}
	
	private void buildMesh() {
		mesh = new MPolygon(5);
		mesh.add(0, -1, 0.5f, 0);
		mesh.add(-1, 0, 0, 0.5f);
		mesh.add(-0.75f, 0.3f, 0.25f, 0.8f);
		mesh.add(0.75f, 0.3f, 0.75f, 0.8f);
		mesh.add(1, 0, 1, 0.5f);
	}

	private void generateTexture() {
		pointGen = new PointGenerator();
		float [][] points = pointGen.generate2d(10, 0, 100);
		variance = new ColorVariance(250, 5, 250, 5, 250, 5);
		colorGen = new ColorGenerator();
		int[] colors = colorGen.generate(applet, 10, variance);

		voronoiGen = new VoronoiTextureGenerator();
		gemTexture = voronoiGen.generate(applet, 100, 100, points, colors);
	}

	public void draw() {
		applet.texture(gemTexture);
		mesh.draw(applet);
	}
}
