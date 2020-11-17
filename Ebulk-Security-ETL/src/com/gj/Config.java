package com.gj;

import java.util.ArrayList;
import java.util.List;

public class Config {

	String tableName;

	
	
	List<Column> columns=new ArrayList<>();
	public Config(String tableName,List<Column> li) {
		super();
		this.tableName = tableName;
		this.columns = li;
		
	}
	public void addCol(Column column) {
		
		columns.add(column);
	}
	public Config() {
		super();
		
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
