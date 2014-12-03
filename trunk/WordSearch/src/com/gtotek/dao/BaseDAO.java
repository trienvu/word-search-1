package com.gtotek.dao;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;

public class BaseDAO extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "ws";
	private static final int DATABASE_VERSION = 1;
//	SELECT * FROM word  WHERE cat_id = 1 LIMIT 1,1
	public BaseDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		// you can use an alternate constructor to specify a database location
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		// super(context, DATABASE_NAME,
		// context.getExternalFilesDir(null).getAbsolutePath(), null,
		// DATABASE_VERSION);

	}
}
