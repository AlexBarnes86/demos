package org.processingaustin.effects;

/**Represents a row within a larger rectangle.  
 * @author Steven Uray
 */
public class Row {
	private final int rowEnd;	
	private final int rowHeight;
	private final int rowNumber;	
	private final int rowStart;
	
	public Row(int rowNum, int rowStartIndex, int rowEndIndex, int rowHeight){
		this.rowNumber = rowNum;
		this.rowStart = rowStartIndex;
		this.rowEnd = rowEndIndex;
		this.rowHeight = rowHeight;	
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public int getRowHeight(){
		return rowHeight;
	}

	public int getRowNumber() {
		return rowNumber;
	}
	
	public int getRowSize(){
		return rowEnd-rowStart;
	}
	
	public int getRowStart() {
		return rowStart;
	}
}