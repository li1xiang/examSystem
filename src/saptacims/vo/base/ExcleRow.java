package saptacims.vo.base;

public class ExcleRow {

	private String rowValue;
	
	private int type;//1.字符串，2.数字，3.年月日，4.年月日时分秒，5.时分秒

	public String getRowValue() {
		return rowValue;
	}

	public void setRowValue(String rowValue) {
		this.rowValue = rowValue;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public ExcleRow(String rowValue,int type){
		this.rowValue=rowValue;
		this.type=type;
	}
	
}
