package com.gao.douban.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gao.douban.R;

public class SplashActivity extends Activity{
	
	private static final String TAG = "SplashActivity";
	private TextView tv_version;
	private LinearLayout LinearLayout01;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		tv_version = (TextView) findViewById(R.id.versionNumber);
		tv_version.setText("版本:"+getVersion());
		
		LinearLayout01 =(LinearLayout) this.findViewById(R.id.LinearLayout01);
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(2000);
		LinearLayout01.startAnimation(aa);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(isNetworkConnected()){
			Log.i(TAG,"进入主界面");
			new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
						Intent intent = new Intent(SplashActivity.this,MyDoubanActivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
			
		}else{
			//弹出对话框 让用户设置网络
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("设置网络");
			builder.setMessage("网络错误请设置网络");
			builder.setPositiveButton("设置网络", new OnClickListener() {				
				public void onClick(DialogInterface dialog, int which) {				
					if(android.os.Build.VERSION.SDK_INT>10){
						 startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));  
					}else{
						 startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));  
					}
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {				
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			builder.create().show();
		}
		
		
		
	}
	
	

	// 获取版本号
	private String getVersion() {
		PackageManager pm = getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return  "";
			//不会发生的
		}		

	}
	
	/**
	 * 判断系统的网络是否可用
	 */
	private boolean isNetworkConnected(){
      ConnectivityManager cm =	(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);		
		NetworkInfo info =cm.getActiveNetworkInfo();
		if(info!=null&&info.isConnected()){
			return true;
		}else {
			return false ;
		}
//		return (info!=null&&info.isConnected());
	}
	
	
	
	

}
