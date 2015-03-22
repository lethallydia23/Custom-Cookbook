package com.example.customcookbook;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/*
 * Lydia Buzzard
 * March 22, 2015
 * 
 * This class displays empty text fields for the user to input the data of a recipe. The user can then save a recipe to store it.
 * 
 */
public class AddRecipe extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_recipe);
		
	}
	
	//Saves the current Recipe
	//-Add support to check if all fields are not empty
	public void saveRecipe(View view)
	{
		//The recipe is complete once the save button has been pressed.
		SharedPreferences.Editor last = getSharedPreferences("Recipe Settings",MODE_PRIVATE).edit();
		last.putBoolean("Complete", true);
		last.commit();
		
		//Notify the user the recipe was stored
		Toast.makeText(AddRecipe.this, "Recipe saved!", Toast.LENGTH_SHORT).show();
		
		//Go back to the previous activity once the recipe has been saved.
		finish();
	}
	
	//Cancels the current recipe, sends user back to previous screen
	public void cancel(View view)
	{
		//We will say the recipe is "complete" when the user presses cancel
		SharedPreferences.Editor last = getSharedPreferences("Recipe Settings",MODE_PRIVATE).edit();
		last.putBoolean("Complete", true);
		last.commit();
		
		//Take user back to previous activity.
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_recipe, menu);
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
