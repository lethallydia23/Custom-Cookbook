package com.example.customcookbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
		
		//Store the recipe in internal memory
		store();
		
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
	
	//Stores the contents of the text field into the phone's internal storage
	public void store()
	{
		//File for storage
		String file = "Recipes";
		
		//Retrieve info from text fields
		//NAME
		EditText name_field = (EditText) findViewById(R.id.name_field);
		String name = name_field.getText().toString();
		//INGREDIENTS
		EditText ingred_field = (EditText) findViewById(R.id.ingredient_field);
		String ingreds = ingred_field.getText().toString();
		//INSTRUCTIONS
		EditText instruct_field = (EditText) findViewById(R.id.instruction_field);
		String instruct = instruct_field.getText().toString();
		
		Recipe newRecipe = new Recipe(name);
		newRecipe.addIngredient(ingreds);
		newRecipe.setSteps(instruct);
		try
		{
			FileOutputStream out = openFileOutput(file, Context.MODE_APPEND);
			if(findAllRecipes()!=null)
			{
				ArrayList<Recipe> oldRecipes = findAllRecipes();
				for(int counter = 0; counter < oldRecipes.size(); counter++)
				{
					System.out.println(oldRecipes.get(counter));
					out.write(oldRecipes.get(counter).toString().getBytes());
				}
			}
			System.out.println(newRecipe);
			out.write(newRecipe.toString().getBytes());
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
	}
	
	public ArrayList<Recipe> findAllRecipes()
	{
		ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
		
		try
		{
			FileInputStream input = new FileInputStream("Recipes");
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
