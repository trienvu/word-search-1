package com.gtotek.entity;

import java.io.Serializable;

import android.database.Cursor;

public class CategoryEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String icon;
	
	public CategoryEntity(){ 
	}
	
	public CategoryEntity(Cursor cursor){ 
		id = 	cursor.getInt(0);
		name = cursor.getString(1);
		icon = cursor.getString(2);
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}



	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + ", icon=" + icon
				+ "]";
	}  
}
