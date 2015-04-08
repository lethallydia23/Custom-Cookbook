package com.example.customcookbook;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		onResume();
		
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		//Find out if a recipe is currently being added
		boolean complete;
		SharedPreferences last = getSharedPreferences("Recipe Settings",MODE_PRIVATE);
		complete = last.getBoolean("Complete", true);
		
		//If so, call the AddRecipe class
		if(!complete)
		{
			Intent intent = new Intent(MainActivity.this, AddRecipe.class);
			startActivity(intent);
		}
		//Otherwise, display the home screen
		else
		{
			setContentView(R.layout.activity_main);
		}
	}

	//This method will be called when the user clicks the "Instructions" button. It generates a dialog box that tells the user how
	//to interact with the program.
	public void instructions(View view)
	{
		new AlertDialog.Builder(this)
		.setTitle("Instructions for Custom Cookbook")
		.setMessage(String.format("ADDING RECIPES:\n1. Click the \"Add Recipe\" button on the home screen.\n2. When OCR"+
		" Instantly Free opens, select and set a language.\n3. Follow the in-app instructions to take a photo of your recipe."+
		" Print usually works best!\n4. Copy the extracted text to the clipboard, then come back to Custom Cookbook."))
		.setPositiveButton("Got it!", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
					dialog.cancel();
				}
			
			})
		.setIcon(android.R.drawable.ic_dialog_alert)
		.show();
	}
	
	//This method is called when the user needs to add a recipe to the cookbook. It is responsible for locating the OCR Instantly Free
	//app and opening it, assuming the app is already installed on the user's device.
	//FUTURE WORK:
	//-Add support for installing the app if it is not on the phone
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
	    	   SharedPreferences.Editor last = getSharedPreferences("Recipe Settings",MODE_PRIVATE).edit();
	    	   last.putBoolean("Complete", false);
	    	   last.commit();
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
	
	public void readRecipes(View view)
	{
		Intent intent = new Intent(MainActivity.this, GetRecipe.class);
		startActivity(intent);
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
