package com.example.killrunningapps;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InstalledAppAdapter extends  BaseAdapter {

	Activity activity;
	ArrayList<AppStructure> appStrucutreList;
	public InstalledAppAdapter(Activity act, ArrayList<AppStructure> appStructure) {
		
		this.activity = act;
		this.appStrucutreList = appStructure;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return appStrucutreList.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
	
		LayoutInflater inflator =(LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
		view = inflator.inflate(R.layout.adapter_layout, null);
		
		TextView appName = (TextView)view.findViewById(R.id.applicationName);
		ImageView appIcon = (ImageView)view.findViewById(R.id.appIcon);
		appName.setText(appStrucutreList.get(arg0).appName);
		appIcon.setBackgroundDrawable(appStrucutreList.get(arg0).appIcons);
		
		return view;
	}

}
