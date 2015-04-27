package com.example.customcookbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowRecipe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipe);
		
		//Get the Recipes to display from the previous Intent
		ArrayList<Parcelable> display = this.getIntent().getParcelableArrayListExtra("Display");
		
		
		if(display!=null)
		{
			if(display.size() != 0)
				show(display);
			else
			{
				TextView noMatches = new TextView(ShowRecipe.this);
				noMatches.setText("There are no matching recipes.");
				noMatches.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
						ViewGroup.LayoutParams.WRAP_CONTENT));
				setContentView(noMatches);
			}
		}
		else
		{
			System.out.println("Display is null");
		}
	}

	public void show(ArrayList<Parcelable> matches)
	{
		ArrayList<Recipe> ready = new ArrayList<Recipe>();
		for(Parcelable next: matches)
		{
			ready.add((Recipe) next);
		}
		ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(ShowRecipe.this, android.R.layout.simple_list_item_1, ready);
		ListView recipeList = new ListView(ShowRecipe.this);
		recipeList.setAdapter(adapter);
		setContentView(recipeList);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_recipe, menu);
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
