package com.gj;

public class Column {

	String columnName;
	String dataType;
	
	boolean isPrimaryKey;
	
	String sourceType;
	String destType;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public Column(String columnName, String dataType, boolean isPrimaryKey) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.isPrimaryKey = isPrimaryKey;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", dataType=" + dataType + ", isPrimaryKey=" + isPrimaryKey
				+ ", sourceType=" + sourceType + ", destType=" + destType + "]";
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getDestType() {
		return destType;
	}
	public void setDestType(String destType) {
		this.destType = destType;
	}
	public Column(String columnName, String dataType, boolean isPrimaryKey, String sourceType, String destType) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.isPrimaryKey = isPrimaryKey;
		this.sourceType = sourceType;
		this.destType = destType;
	}
	
	
}
