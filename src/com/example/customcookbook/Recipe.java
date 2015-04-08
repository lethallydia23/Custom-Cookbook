package com.example.customcookbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Lydia Buzzard
 * March 20, 2015
 * 
 * This class represents a Recipe object to be created within the MainActivity. Parameters include a recipe name, list of ingredients,
 * and a StringBuffer of steps. The class contains default and initial value constructors along with basic set/get methods and a
 * toString method for printing the Recipe.
 */
public class Recipe 
{
	//Parameters
	String name;
	String ingredients;
	//Maybe add an image parameter to store photo of recipe?
	String steps;	//If we save the image, this param may be deleted
	
	//Default constructor
	public Recipe()
	{
		//Initialize empty params
		name="";
		ingredients = "";
		steps = "";
		
	}
	
	//Initial value constructor
	public Recipe(String name)
	{
		this.name = name;
		ingredients = "";
		steps = "";
	}
	
	//SET AND GET METHODS
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addIngredient(String ingred)
	{
		ingredients = ingred;
	}
	
	public void setSteps(String steps)
	{
		this.steps  = steps;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getIngredient()
	{
		return ingredients;
	}
	
	public String getSteps()
	{
		return this.steps;
	}
	
	//This method reads all Recipes from the app's "Recipes" text file
	public static ArrayList<Recipe> findAll(FileInputStream input)
	{
		boolean done = false;
		int index = 0;
		ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
		try
		{
			byte [] bytes = new byte[input.available()];
			input.read(bytes);
			String info = new String(bytes);
			Recipe next;
			
			//This StringBuffer contains all recipes stored.
			StringBuffer result = new StringBuffer(info);
			
			System.out.println(result);
			done = result.length() == index;
			
			//Create Recipe objects
			while(!done)
			{
				next = new Recipe();
				next.setName(result.substring(result.indexOf("RECIPE:"+8), result.indexOf("INGREDIENTS:")));
				next.addIngredient(result.substring(result.indexOf("INGREDIENTS:"+13), result.indexOf("INSTRUCTIONS:")));
				next.setSteps(result.substring(result.indexOf("INSTRUCTIONS:"+14), result.length()));
				
				//Add Recipe objects to the list
				allRecipes.add(next);
				
				done = result.length() == index;
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
		
		return allRecipes;
	}
	
	public boolean contains(String keyword)
	{
		return (this.name.contains(keyword) || this.ingredients.contains(keyword) || this.steps.contains(keyword));
	}
	
	public String toString()
	{
		String result = "";
		
		result = String.format("RECIPE: %s\n", name);
		
		result+="INGREDIENTS:\n"+ingredients+"\n";
		
		result+="INSTRUCTIONS:\n"+steps;
		
		return result;
	}

}
