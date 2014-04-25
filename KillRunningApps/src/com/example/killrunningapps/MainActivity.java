package com.example.killrunningapps;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	ArrayList<AppStructure> appStructurelist = new ArrayList<AppStructure>();

	AppStructure appStructure;
    ListView listView;
    List<RunningTaskInfo> runningAPps; 
    Button killApps;
	 ActivityManager activityManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity);
		listView = (ListView) findViewById(R.id.appList);
		killApps = (Button)findViewById(R.id.kilBtn);
		activityManager = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);

		
		runningAPps = activityManager
	            .getRunningTasks(Integer.MAX_VALUE);
		for (int i = 0; i < runningAPps.size(); i++) {
			appStructure = new AppStructure();
			
			try {
				appStructure.appName =(String) getPackageManager().getApplicationLabel(getPackageManager()
                        .getApplicationInfo(runningAPps.get(i).topActivity.getPackageName(),
                                PackageManager.GET_META_DATA));
				appStructure.appIcons = getPackageManager().getApplicationIcon(runningAPps.get(i).topActivity.getPackageName());
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			appStructurelist.add(appStructure);
			
		}

		killApps.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				for (int i = 0; i < runningAPps.size(); i++) {
					if(!runningAPps.get(i).topActivity.getPackageName().equalsIgnoreCase("com.example.retriveinstallapps")){
						activityManager.killBackgroundProcesses(runningAPps.get(i).topActivity.getPackageName());
					
					}
					try {
						appStructure.appName =(String) getPackageManager().getApplicationLabel(getPackageManager()
		                        .getApplicationInfo(runningAPps.get(i).topActivity.getPackageName(),
		                                PackageManager.GET_META_DATA));
						appStructure.appIcons = getPackageManager().getApplicationIcon(runningAPps.get(i).topActivity.getPackageName());
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}
					appStructurelist.clear();
					appStructurelist.add(appStructure);
					
				}
				
				 updateAdpater();
			}
		});
		
		 updateAdpater();
	}
	
	
	public void updateAdpater(){
		InstalledAppAdapter adapter = new InstalledAppAdapter(MainActivity.this,
				appStructurelist);
		listView.setAdapter(adapter);
	}
	
	
	
}
