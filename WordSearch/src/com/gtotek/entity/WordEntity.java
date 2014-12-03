package com.gtotek.entity;

import android.database.Cursor;

public class WordEntity {

	private int id;
	private String content;
	private int cat_id;

	public WordEntity() {
	}

	public WordEntity(Cursor cursor) {
		id = cursor.getInt(0);
		content = cursor.getString(1);
		cat_id = cursor.getInt(2);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	@Override
	public String toString() {
		return "WordEntity [id=" + id + ", content=" + content + ", cat_id="
				+ cat_id + "]";
	}
}
