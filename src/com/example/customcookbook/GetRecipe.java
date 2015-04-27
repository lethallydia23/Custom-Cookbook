package com.example.customcookbook;

import java.io.FileInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GetRecipe extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_recipe);
		
		ArrayList<Recipe> recipes = findAll();
		
		System.out.println(recipes.size());
		Intent send = new Intent();
		if(!recipes.isEmpty())
		{
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList("Recipes", recipes);
		send.putExtras(bundle);
		setResult(Activity.RESULT_OK, send);
		finish();
		}
		else
		{
			setResult(Activity.RESULT_CANCELED, send);
			System.out.println("Recipes were empty");
			finish();
		}
		
	}
	
	//This method reads all Recipes from the app's "Recipes" text file
	public ArrayList<Recipe> findAll()
	{
		ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
		
		try
		{
			FileInputStream input = openFileInput("Recipes");
			byte [] bytes = new byte[input.available()];
			input.read(bytes);
			String info = new String(bytes);
			StringBuffer result = new StringBuffer(info);
			
			//Check this
			while(result.length() != 0)
				result = Recipe.findNext(allRecipes, result);
			
			input.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return allRecipes;
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_recipe, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
