package com.gtotek.adapter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gtotek.wordsearch.R;
import com.gtotek.wordsearch.Word;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WordAdapter extends BaseAdapter {
	private Context mContext;
	private List<Word> mWords;
	private Set<Word> mFoundWords;

	public WordAdapter(Context context, List<Word> words) {
		mContext = context;
		mWords = words;
		mFoundWords = new HashSet<Word>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mWords.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mWords.get(position);
	}
	
	public void setWordsFound(Collection<Word> words) {
		mFoundWords.addAll(words);
	}

	public void setWordFound(Word word) {
		mFoundWords.add(word);
		notifyDataSetChanged();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (v == null) {
			v = LayoutInflater.from(mContext).inflate(R.layout.wordsearch_word,
					null);
		} 
		
		Word word = (Word) getItem(position);
		TextView tv = (TextView) v.findViewById(R.id.lbl_word); 
		String wordStr = word.getWord().toLowerCase();
		wordStr = wordStr.substring(0, 1).toUpperCase() + wordStr.substring(1);
		tv.setText(wordStr);
		if (!mFoundWords.contains(word)) {
			tv.setTextColor(Color.parseColor("#3a3a3a")); 
			tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
		} else {
			tv.setTextColor(Color.parseColor("#FF0000")); 
			tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
		return v;
	}

}
