package org.processingaustin.effects;

import java.util.Collections;
import java.util.List;

/**Represents a series of horizontal rows of pixels within a larger rectangle or square of pixels, 
 * such as the screen in processing. Rows are guaranteed to be in ascending order.
 * @author Steven Uray
 */
public class RowList {
	private final List<Row> rows;
	
	public List<Row> getRowsUnmodifable(){
		return Collections.unmodifiableList(rows);
	}
		
	public RowList(List<Row> rows){
		this.rows = rows;
	}	
}