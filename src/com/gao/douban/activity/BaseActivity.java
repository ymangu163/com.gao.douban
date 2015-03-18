package com.gao.douban.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gao.douban.R;

/*
 *   实现公司采用的项目框架
 */
public abstract class BaseActivity extends ActionBarActivity  implements OnClickListener{

	
	private LayoutInflater inflater;
    protected LinearLayout bottomMenu;
    private TextView tvMyDouban, tvNewBook, tvBookComment, tvSearch,tvAbout;
    protected View loading;
    private View tip;
    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		// 把子类的布局加载进来
		 ViewGroup rootView = (ViewGroup) inflater.inflate(getContentView(), null);
		 // 把底部布局加载进来
		 bottomMenu = (LinearLayout) rootView.findViewById(R.id.layout_bottom_menu);
		 if (bottomMenu != null) {  //找到底部菜单的控件
			 tvMyDouban = (TextView) rootView.findViewById(R.id.tv_my_douban);
			 tvNewBook = (TextView) rootView.findViewById(R.id.tv_new_book);
			 tvBookComment = (TextView) rootView.findViewById(R.id.tv_book_comment);
			 tvSearch = (TextView) rootView.findViewById(R.id.tv_search);
			 tvAbout = (TextView) rootView.findViewById(R.id.tv_about);
			 
			 tvMyDouban.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
			 tvNewBook.setOnClickListener(new OnClickListener() {
				 
				 @Override
				 public void onClick(View v) {
					 
				 }
			 });
			 tvBookComment.setOnClickListener(new OnClickListener() {
				 
				 @Override
				 public void onClick(View v) {
					 
				 }
			 });
			 tvSearch.setOnClickListener(new OnClickListener() {
				 
				 @Override
				 public void onClick(View v) {
					 
				 }
			 });
			 tvAbout.setOnClickListener(new OnClickListener() {
				 
				 @Override
				 public void onClick(View v) {
					 
				 }
			 });
			 
		 }
		
		 loading = initLoading();
	      tip = initTip();
		 LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		 //适配子类的布局
		 if(rootView instanceof RelativeLayout){ //若是RelativeLayout
			 RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			 lp.addRule(RelativeLayout.CENTER_IN_PARENT);
			 rootView.addView(loading, lp);
	         rootView.addView(tip, lp);
		 }else if (rootView instanceof LinearLayout) {
	            rootView.addView(loading, 1, params);
	            rootView.addView(tip, 2, params);

	        }else if (rootView instanceof ScrollView) {
	        	  LinearLayout linearLayout = new LinearLayout(this);
	        	  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	                      LinearLayout.LayoutParams.MATCH_PARENT,
	                      LinearLayout.LayoutParams.MATCH_PARENT);
	        	     linearLayout.setLayoutParams(layoutParams);
	        	     linearLayout.addView(rootView);
	        	     rootView = linearLayout;
	        	     rootView.addView(loading, 0, params);
	        	     rootView.addView(tip, 1, params);
	        }
		  setContentView(rootView);
		  try {
	            initView(rootView);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }	 
		
	}

	private TextView messageTV;
    /**
     * 初始化默认的提示布局
     */
	 private View initTip() {
		  View view = inflater.inflate(R.layout.not_data_layout, null);
		  messageTV = (TextView) view.findViewById(R.id.not_data_message);
	        view.setVisibility(View.GONE);
		 return view;
	}

	private ImageView loadingImage;
	 private View page;
	 protected View reload;
	private View initLoading() {
		 View view = inflater.inflate(R.layout.loading, null);  //下载时显示的动画页
		 loadingImage = (ImageView) view.findViewById(R.id.img_loading);
		 loadingImage.setBackgroundResource(R.drawable.loading_dialog);  //改成小鸭子的动画
		 loadingImage.post(new Runnable() {
	            @Override
	            public void run() {
	                AnimationDrawable frameAnimation = (AnimationDrawable) loadingImage.getBackground();
	                frameAnimation.start();
	            }
	        });
		 page = view.findViewById(R.id.layout_page);
		 reload = view.findViewById(R.id.reload_layout_page);
		 reload.setOnClickListener(new OnClickListener() {  //点击重新加载
	            @Override
	            public void onClick(View v) {
	                reLoad();
	            }
	        });
		 view.setVisibility(View.GONE);
		return view;
	}


	@Override
	public void onClick(View v) {
		
	}
	
	  // ---------------- 以下是给子类实现的抽象方法 ----------------------//
	/**
     * 返回布局的id，例如R.layout.myactivity_layout
     */
    protected abstract int getContentView();
    
    /**
     * 加载数据失败之后点击重新加载会调用此方法
     */
    protected abstract void reLoad();
    
    /**
     * 初始化view
     */
    protected abstract void initView(View view) throws Exception;
    
	
}
