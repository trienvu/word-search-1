package com.gtotek.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil
{

	protected SharedPreferences mSp;

	public SharedPreferencesUtil(Context context, String name)
	{
		mSp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	public void clear()
	{
		mSp.edit().clear().commit();
	}

	public boolean put(String key, boolean value)
	{
		return mSp.edit().putBoolean(key, value).commit();
	}

	public boolean put(String key, int value)
	{
		return mSp.edit().putInt(key, value).commit();
	}

	public boolean put(String key, long value)
	{
		return mSp.edit().putLong(key, value).commit();
	}

	public boolean put(String key, float value)
	{
		return mSp.edit().putFloat(key, value).commit();
	}

	public boolean put(String key, String value)
	{
		return mSp.edit().putString(key, value).commit();
	}

	public boolean getBoolean(String key, boolean defaultValue)
	{
		return mSp.getBoolean(key, defaultValue);
	}

	public int getInt(String key, int defaultValue)
	{
		return mSp.getInt(key, defaultValue);
	}

	public long getLong(String key, long defaultValue)
	{
		return mSp.getLong(key, defaultValue);
	}

	public float getFloat(String key, float defaultValue)
	{
		return mSp.getFloat(key, defaultValue);
	}

	public String getString(String key, String defaultValue)
	{
		return mSp.getString(key, defaultValue);
	}

	public void remove(String key)
	{
		mSp.edit().remove(key).commit();
	}

	public SharedPreferences getSharedPreferences()
	{
		return mSp;
	}

	public Editor getEditor()
	{
		return mSp.edit();
	}

}