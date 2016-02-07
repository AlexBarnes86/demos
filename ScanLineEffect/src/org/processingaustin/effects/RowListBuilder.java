package org.processingaustin.effects;

import java.util.ArrayList;
import java.util.List;

/**Takes a square area of pixels and breaks it down into an array of horizontal rows. 
 * @author Steven Uray
 */
public class RowListBuilder {
	
	public RowList getRows(Rectangle rectangle, int rowHeight){
		int rowCount = Math.floorDiv(rectangle.getHeight(), rowHeight);	
		List<Row> rows = new ArrayList<Row>();
		
		for(int i = 0; i < rowCount; i++){
			int rowStart = rectangle.getWidth()*i;
			int rowSize = rectangle.getWidth()*rowHeight;
			int rowEnd = rowStart+rowSize-1;			
			Row nextRow = new Row(i,rowStart,rowEnd,rowHeight);
			rows.add(nextRow);
		}
		return new RowList(rows);
	}	
}