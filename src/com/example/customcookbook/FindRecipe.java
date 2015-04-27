package com.example.customcookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FindRecipe extends Activity 
{

	private static final int GET_RECIPE_REQUEST = 1;
	public static String keyword;	//Value set in beginSearch from text field
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_recipe);
		
		Intent find = new Intent(FindRecipe.this, GetRecipe.class);
		find.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(find, GET_RECIPE_REQUEST);
	}
	
	protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData) 
	{
		switch (aRequestCode) 
	    {
		case GET_RECIPE_REQUEST:
			if(aResultCode == Activity.RESULT_OK)
			{
				Intent display = new Intent(FindRecipe.this, ShowRecipe.class);
				display.putExtra("Display", search(aData.getParcelableArrayListExtra("Recipes")));
				startActivity(display);
			}
			break;
		default:
			break;	
	    }
		super.onActivityResult(aRequestCode, aResultCode, aData);
	}
	
	public void getAllRecipes()
	{
		Intent find = new Intent(FindRecipe.this, GetRecipe.class);
		find.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(find, GET_RECIPE_REQUEST);
	}
	
	public void beginSearch(View view)
	{
		EditText searchBar = (EditText) findViewById(R.id.search_bar);
		keyword = searchBar.getText().toString();
		
		getAllRecipes();
		
	}
	
	public ArrayList<Recipe> search(ArrayList<Parcelable> recipes)
	{
		@SuppressWarnings("unchecked")
		ArrayList<Parcelable> allRecipes = recipes;
		ArrayList<Recipe> matches = new ArrayList<Recipe>();
		
		if(recipes != null)
		{
			System.out.printf("Number of stored recipes found is %d", allRecipes.size());
			for(Parcelable current: allRecipes)
			{
				Recipe newRecipe = (Recipe) current;
				if(newRecipe.contains(keyword))
					matches.add(newRecipe);
			}
		}
		else
		{
			System.out.println("The list is null");
		}
		return matches;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_recipe, menu);
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
