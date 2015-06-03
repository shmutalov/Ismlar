package com.epikur.ismlar;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class EmbeddedSqliteDatabase extends SQLiteAssetHelper {
	public EmbeddedSqliteDatabase(Context context, String databaseName) {
		super(context, databaseName, null, 1);
	}
}
