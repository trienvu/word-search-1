package com.gtotek.adapter;

import java.util.List;

import com.gtotek.entity.CategoryEntity;
import com.gtotek.wordsearch.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CategoryAdapter extends BaseAdapter {
	private List<CategoryEntity> mCategoryEntitys;
	private LayoutInflater mLayoutInflater;
	private Context mContext;

	public CategoryAdapter(Context ctx, List<CategoryEntity> categoryEntities) {
		mCategoryEntitys = categoryEntities;
		mLayoutInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = ctx; 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCategoryEntitys.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCategoryEntitys.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CategoryEntity categoryEntity = (CategoryEntity) getItem(position);
		
		ViewHolder viewHolder;

		if (null == convertView) {
			convertView = mLayoutInflater.inflate(R.layout.cell_category,
					parent, false);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		int resourceId =	mContext.getResources().getIdentifier(categoryEntity.getIcon(), "drawable", 
				mContext.getPackageName());
		
		viewHolder.imgIcon.setImageResource(resourceId);

		return convertView;
	}

	private static class ViewHolder {
		private ImageView imgIcon;

		public ViewHolder(View v) {
			imgIcon = (ImageView) v.findViewById(R.id.imgIcon);
			v.setTag(this);
		}
	}

}
