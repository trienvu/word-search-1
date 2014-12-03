package com.gtotek.dao;

import java.util.ArrayList;
import java.util.List;

import com.gtotek.entity.CategoryEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDAO extends BaseDAO {

	public CategoryDAO(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<CategoryEntity> getAllCategoryEntitys() {
		List<CategoryEntity> categoryEntitys = new ArrayList<CategoryEntity>();

		String sql = "SELECT * FROM category";

		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery(sql, null);

		while (cursor.moveToNext()) {
			CategoryEntity categoryEntity = new CategoryEntity(cursor);
			categoryEntitys.add(categoryEntity);
		}

		return categoryEntitys;
	}

}
