package com.foodkonnekt.model;


import java.util.ArrayList;

public class ExcelWorksheet {

	private String name;
	/*private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();*/
	private ArrayList<Items> items = new ArrayList<Items>();
	public ArrayList<Items> getItems() {
		return items;
	}

	public void setItems(ArrayList<Items> items) {
		this.items = items;
	}

	private int maxCols = 0;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*public void addRow(ArrayList<Object> row) {
		data.add(row);
		if(maxCols<row.size()) {
			maxCols = row.size();
		}
	}
	
	public int getMaxRows() {
		return data.size();
	}
	
	public void fillColumns() {
		for(ArrayList<Object> tmp: data) {
			while(tmp.size()<maxCols) {
				tmp.add(null);
			}
		}
	}
	
	// GET/SET
	
	

	public ArrayList<ArrayList<Object>> getData() {
		return data;
	}

	public void setData(ArrayList<ArrayList<Object>> data) {
		this.data = data;
	}
	
	public int getMaxCols() {
		return maxCols;
	}
	
	public void setMaxCols(int maxCols) {
		this.maxCols = maxCols;
	}*/
}
