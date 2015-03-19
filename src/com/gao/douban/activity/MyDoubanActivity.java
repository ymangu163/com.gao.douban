package com.gao.douban.activity;

import com.gao.douban.R;
import com.gao.douban.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyDoubanActivity extends BaseActivity {

	
	@Override
	protected int getContentView() {

		return R.layout.activity_my_douban;
	}

	@Override
	protected void reLoad() {
		
	}

	@Override
	protected void initView(View view) throws Exception {
		setTitle("我的豆瓣");

	}

	private void setActionBar() {
		 ActionBar actionBar = getSupportActionBar();
	        actionBar.setDisplayShowHomeEnabled(false);
	        actionBar.setDisplayShowTitleEnabled(false);
		
		
		
	}
}
