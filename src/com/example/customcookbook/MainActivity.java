package com.example.customcookbook;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	
	public void findOCR(View view)
	{  
		//Code from StackOverflow user Sahil Mahajan Mj (Posted Dec 3 2012 @ 
		//http://stackoverflow.com/questions/13682365/get-all-installed-applications-along-with-settings-messages-camera-only-those-a
		PackageManager pm = this.getPackageManager();
	    Intent intent = new Intent(Intent.ACTION_MAIN, null);
	    intent.addCategory(Intent.CATEGORY_LAUNCHER);
	    List<ResolveInfo> list;
	    list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
	    for (ResolveInfo rInfo : list)
	    {
	       String str = rInfo.activityInfo.applicationInfo.loadLabel(pm).toString() + "\n";
	       
	       if(str.contains("OCR Instantly Free"))
	       {
	    	   String packName = rInfo.activityInfo.packageName;
	    	   //Code from community wiki @ 
	    	   //http://stackoverflow.com/questions/2780102/open-another-application-from-your-own-intent/7596063#7596063
	    	   Intent openApp = pm.getLaunchIntentForPackage(packName);
	    	   openApp.addCategory(Intent.CATEGORY_LAUNCHER);
	    	   this.startActivity(openApp);
	    	   //End community wiki code

	       }
	    }
	    //End StackOverflow Code
	    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
