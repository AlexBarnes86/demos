package org.processingaustin.effects;

import java.util.List;

import processing.core.PApplet;

public class ScanLineEffectCreator {
	private final RowList rows;
	private final PApplet pApplet;
	
	public ScanLineEffectCreator(PApplet pApplet,Rectangle areaOfEffect){
		this.pApplet = pApplet;
		RowListBuilder rowListBuilder = new RowListBuilder();
		//TODO parameterize row height variable
		int rowHeight = 1;
		this.rows = rowListBuilder.getRows(areaOfEffect, rowHeight);		
	}
	
	public void createScanLines(int scanLineColor){
		pApplet.loadPixels();
		List<Row> rowsList = rows.getRowsUnmodifable();			
		for(int i = 0; i < rowsList.size(); i++){			
			boolean shouldApplyEffectToRow = isNumberEven(i);	
			if(shouldApplyEffectToRow){
				Row row = rowsList.get(i);
				applyScanLineEffectToRow(row,scanLineColor);
			}		
		}				
		pApplet.updatePixels();
	}

	private void applyScanLineEffectToRow(Row row, int scanLineColor) {	
		for(int i = 0; i < row.getRowSize(); i++){
			int nextRowIndex = row.getRowStart()+i;
			int nextPixelColor = scanLineColor;			
			pApplet.pixels[nextRowIndex] = nextPixelColor;
		}		
	}

	private boolean isNumberEven(int rowNumber) {
		int remainder = rowNumber % 2;
		if(remainder == 0){
			return true; 
		} else{
			return false; 
		}
	}
}
