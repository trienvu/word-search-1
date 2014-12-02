package com.gtotek.wordsearch;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class WordListAdapter extends BaseAdapter implements ListAdapter {

	private Context mContext;
	private List<Word> mWords;
	private Set<Word> mFoundWords;

	/**
	 * 
	 */
	public WordListAdapter(Context context, List<Word> words) {
		mContext = context;
		mWords = words;
		mFoundWords = new HashSet<Word>();
	}

	public int getCount() {
		return mWords.size();

	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	public void setWordsFound(Collection<Word> words) {
		mFoundWords.addAll(words);
	}

	public void setWordFound(Word word) {
		mFoundWords.add(word);
		notifyDataSetChanged();
	}

	public Object getItem(int position) {
		return mWords.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = LayoutInflater.from(mContext).inflate(R.layout.wordsearch_word,
					null);
		} 
		
		Word word = (Word) getItem(position);
		TextView tv = (TextView) v.findViewById(R.id.lbl_word);
		v.setFocusable(false);
		tv.setFocusable(false);
		String wordStr = word.getWord().toLowerCase();
		wordStr = wordStr.substring(0, 1).toUpperCase() + wordStr.substring(1);
		tv.setText(wordStr);
		if (!mFoundWords.contains(word)) {
			tv.setTextColor(Color.parseColor("#3a3a3a"));
			tv.setTypeface(Typeface.DEFAULT_BOLD);
			tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
		} else {
			tv.setTextColor(Color.parseColor("#FF0000"));
			tv.setTypeface(Typeface.DEFAULT);
			tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}

		return v;
	}
}
