package com.gtotek.wordsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.gtotek.adapter.CategoryAdapter;
import com.gtotek.adapter.dialog.DialogFAQ;
import com.gtotek.dao.CategoryDAO;
import com.gtotek.entity.CategoryEntity;
import com.gtotek.util.PlayMusic;

public class CategoryActivity extends Activity {
	private Context mContext = this;

	private GridView mGrvCategory;
	private CategoryDAO mCategoryDAO;

	private DialogFAQ dialogFAQ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		initUI();
		
		playMusic();
		
		dialogFAQ = new DialogFAQ(CategoryActivity.this);
		dialogFAQ.setStrTitle("FAQ NOTE");
		dialogFAQ.setStrFuntion("Search App from hilarious");
		try {
			dialogFAQ.setStrVersion("Version: "+this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		dialogFAQ.setStrOk("OK");
		dialogFAQ.setStrNameDev("Deverloper: GTOTEK TEAM");
		dialogFAQ.show();
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

	private PlayMusic playMusic;
	
	private void playMusic(){
		if(playMusic == null){
			playMusic = new PlayMusic(CategoryActivity.this);
		}
//		final int sRandom = new Random().nextInt(SoundConfig.arrSound.length);
		new Handler().post(new Runnable() {
			
			@Override
			public void run() {
				playMusic.play();
			}
		});
		
	}
	
	private void stopMusic(){
		if(playMusic != null && playMusic.getmMediaPlayer().isPlaying()){
			playMusic.getmMediaPlayer().stop();
			playMusic.stop();
		}else{
			playMusic.stop();
		}
	}
	@Override
	public void onBackPressed() {
//		super.onBackPressed();
		stopMusic();
		CategoryActivity.this.finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(playMusic == null){
			playMusic = new PlayMusic(CategoryActivity.this);
		}
			playMusic();
	}
	
	
}
