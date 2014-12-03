package com.gtotek.dao;

import java.util.Random;

import com.gtotek.entity.WordEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WordDAO extends BaseDAO {

	public WordDAO(Context context) {
		super(context);
	}

	public String[] getRndWordEntity(int cat_id) {
		WordEntity entity = null;
		String[] ss = null;
		String sql = "SELECT * FROM word  WHERE cat_id = ? LIMIT ?,1";

		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		
		int size = getSizeOfCat_id(cat_id);

		int index = new Random().nextInt(size);

		Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] {
				cat_id + "", index + "" });

		if (cursor.moveToFirst()) {
			entity = new WordEntity(cursor);

			String content = entity.getContent();
			content = content.toUpperCase();
			ss = content.split(" ");
		}

		sqLiteDatabase.close();
		cursor.close();

		return ss;
	}

	public int getSizeOfCat_id(int cat_id) {
		String sql = "SELECT count(*) AS `count` FROM word  WHERE cat_id = ?";

		SQLiteDatabase sqLiteDatabase = getReadableDatabase();

		Cursor cursor = sqLiteDatabase.rawQuery(sql,
				new String[] { cat_id + "" });
		int size = -1;
		if (cursor.moveToFirst()) {
			size = cursor.getInt(0);
		}

		cursor.close();

		return size;
	}
}
