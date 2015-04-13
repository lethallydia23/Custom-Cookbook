package com.example.customcookbook;

import java.io.FileInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class GetRecipe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_recipe);
		
		ArrayList<Recipe> recipes = findAll();
		
		TextView recipeText = new TextView(GetRecipe.this);
		
		for(int counter  = 0; counter < recipes.size(); counter++)
		{
			recipeText.setText(""+recipeText.getText()+recipes.get(counter));
		}
		
		ScrollView scroll = (ScrollView) findViewById(R.id.scroll);
		scroll.addView(recipeText);
		setContentView(scroll);
		
	}
	
	//This method reads all Recipes from the app's "Recipes" text file
	public ArrayList<Recipe> findAll()
	{
		ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
		
		try
		{
			FileInputStream input = openFileInput("Recipes");
			allRecipes = Recipe.findAll(input);
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
