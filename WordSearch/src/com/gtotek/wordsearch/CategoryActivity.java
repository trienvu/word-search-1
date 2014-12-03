package com.gtotek.wordsearch;

import com.gtotek.adapter.CategoryAdapter;
import com.gtotek.dao.CategoryDAO;
import com.gtotek.entity.CategoryEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryActivity extends Activity {
	private Context mContext = this;

	private GridView mGrvCategory;
	private CategoryDAO mCategoryDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		initUI();
	}

	private void initUI() {
		mCategoryDAO = new CategoryDAO(mContext);

		mGrvCategory = (GridView) findViewById(R.id.grvCategory);
		mGrvCategory.setAdapter(new CategoryAdapter(mContext, mCategoryDAO
				.getAllCategoryEntitys()));
		mGrvCategory.setOnItemClickListener(mOnItemClickListener);
	}

	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			CategoryEntity categoryEntity = (CategoryEntity) parent
					.getItemAtPosition(position);

			Intent intent = new Intent(mContext, MainActivity.class);
			intent.putExtra(Define.KEY_CATEGORY, categoryEntity);
			startActivity(intent);

		}
	};
}
