package org.processingaustin.executables;
import org.processingaustin.effects.Rectangle;
import org.processingaustin.effects.ScanLineEffectCreator;

import processing.core.PApplet;
import processing.core.PImage;

public class ScanLineEffectDemo extends PApplet{
	private static final int APP_HEIGHT = 1013;
	private static final int APP_WIDTH = 1920;
	private static ScanLineEffectCreator scanLineCreator;
	
	public static void main(String[] args) {
		String sketchPath = "org.processingaustin.executables.ScanLineEffectDemo";
		PApplet.main(new String[] { "--present", sketchPath});	
	}

	public void settings() {
		size(APP_WIDTH,APP_HEIGHT);
	}
	
	public void setup(){
		colorMode(HSB,255,255,255,255);		
		setScanLineCreator();
		loadBackgroundImage();		
	}
	
	private void loadBackgroundImage() {
		background(255);	
		String imageName = "/ScanLineEffectTestImage1.jpg";
		String imagePath = ScanLineEffectDemo.class.getResource(imageName).getFile();		
		PImage pImage = loadImage(imagePath);	
		image(pImage,0,0);
	}

	private void setScanLineCreator() {
		PApplet pApplet = this;
		Rectangle areaOfEffect = new Rectangle(APP_WIDTH,APP_HEIGHT);		
		scanLineCreator = new ScanLineEffectCreator(pApplet,areaOfEffect);
	}

	public void draw(){
		int scanLineColor = color(0,0,0,255);		
		scanLineCreator.createScanLines(scanLineColor);		
	}
}