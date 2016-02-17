package megamu.mesh;

import processing.core.*;

public class MPolygon {

	float[][] coords;
	int count;
	
	public MPolygon(){
		this(0);
	}

	public MPolygon(int points){
		coords = new float[points][4];
		count = 0;
	}

	public void add(float x, float y){
		coords[count][0] = x;
		coords[count][1] = y;
		count++;
	}
	
	public void add(float x ,float y, float u, float v) {
		coords[count][0] = x;
		coords[count][1] = y;
		coords[count][2] = u;
		coords[count][3] = v;
		count++;
	}

	public void draw(PApplet p){
		draw(p.g);
	}

	public void draw(PGraphics g){
		g.beginShape();
		for(int i=0; i<count; i++){
			g.vertex(coords[i][0], coords[i][1], coords[i][2], coords[i][3]);
		}
		g.endShape(PApplet.CLOSE);
	}

	public int count(){
		return count;
	}

	public float[][] getCoords(){
		return coords;
	}

}