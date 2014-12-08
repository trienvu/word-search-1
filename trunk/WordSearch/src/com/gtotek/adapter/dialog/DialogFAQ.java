/**
 * @(#) MyDialog
 *
 * Copyright (c) 2010 Browan Communications, Inc.
 * All rights reserved.
 */
package com.gtotek.adapter.dialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.gtotek.wordsearch.R;

public class DialogFAQ
{
//	private Context mContext;
	private Dialog mDialog;
	
	
	private String strTitle;
	private String strFuntion;
	private String strVersion;
	private String strNameDev;
	private String strOk;
	public DialogFAQ(Context context)
	{
		mDialog = new Dialog(context);
		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mDialog.setContentView(R.layout.dialog_faq_layout);
		

	}
	
	private void initUIDialog(){
		TextView tvTitle = (TextView)mDialog.findViewById(R.id.tvTitle);
		TextView tvFuncion = (TextView)mDialog.findViewById(R.id.tvFuncion);
		TextView tvVersion = (TextView)mDialog.findViewById(R.id.tvVersion);
		TextView tvDevName = (TextView)mDialog.findViewById(R.id.tvDevName);
		TextView tvOke = (TextView)mDialog.findViewById(R.id.tvOke);
		
		tvTitle.setText(getStrTitle());
		tvFuncion.setText(getStrFuntion());
		tvOke.setText(getStrOk());
		tvVersion.setText(getStrVersion());
		tvDevName.setText(getStrNameDev());
		
		tvOke.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mDialog.dismiss();
			}
		});
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrFuntion() {
		return strFuntion;
	}

	public void setStrFuntion(String strFuntion) {
		this.strFuntion = strFuntion;
	}

	public String getStrVersion() {
		return strVersion;
	}

	public void setStrVersion(String strVersion) {
		this.strVersion = strVersion;
	}

	public String getStrNameDev() {
		return strNameDev;
	}

	public void setStrNameDev(String strNameDev) {
		this.strNameDev = strNameDev;
	}

	public String getStrOk() {
		return strOk;
	}

	public void setStrOk(String strOk) {
		this.strOk = strOk;
	}

	/**
	 * Dissmis Dialog
	 */
	public void dismiss()
	{
		if(mDialog != null && mDialog.isShowing()){
			mDialog.dismiss();
		}
		
	}
	/**
	 * show Dialog
	 */
	public void show()
	{
		if(mDialog != null){
			initUIDialog();
			mDialog.show();
		}
		
	}

//	@Override
//	public void onClick(View v)
//	{
//		// TODO Auto-generated method stub
//		switch (v.getId())
//		{
//
//		case R.id.OK:
//			dismiss();
//			((CaptureActivity) (mContext)).refreshActivity();
//			break;
//		default:
//			break;
//		}
//	}

//	public void bindHandler(Handler handler)
//	{
//		mHandler = handler;
//	}

	
}