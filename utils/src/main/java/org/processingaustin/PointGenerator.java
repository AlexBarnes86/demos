package org.processingaustin;

public class PointGenerator {
	public float[][] generate2d(int nPoints, float min, float max) {
		float [][] points = new float[nPoints][2];
		float range = max-min;
		for(int i = 0; i < nPoints; ++i) {
			points[i][0] = (float)(min+Math.random()*range);
			points[i][1] = (float)(min+Math.random()*range);
		}
		return points;
	}
}
