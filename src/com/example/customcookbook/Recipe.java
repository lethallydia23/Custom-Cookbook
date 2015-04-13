package com.example.customcookbook;

import java.io.File;
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
			input.close();
			
			//This StringBuffer contains all recipes stored.
			StringBuffer result = new StringBuffer(info);
			
			//TEST THAT INFO IS READ
			done = result.length() == index;
			
			//Create Recipe objects
			while(!done)
			{
				next = new Recipe();
				next.setName(result.substring(result.indexOf("RECIPE:")+8, result.indexOf("INGREDIENTS:")));
				result.delete(0, result.indexOf("INGREDIENTS:"));
				System.out.println(next.name);
				next.addIngredient(result.substring(12, result.indexOf("INSTRUCTIONS:")));
				result.delete(0, result.indexOf("INSTRUCTIONS:"));
				System.out.println(next.ingredients);
				
				try
				{
					next.setSteps(result.substring(14, result.indexOf("RECIPE:")));
				}
				catch(Exception d)
				{
					next.setSteps(result.substring(14, result.length()));
				}
				
				System.out.println(next.steps);
				
				//Add Recipe objects to the list
				allRecipes.add(next);
				
				done = result.indexOf("RECIPE:", result.indexOf("INSTRUCTIONS:")) < 0;
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
		
		result+="INSTRUCTIONS:\n"+steps+"\n"+"\n";
		
		return result;
	}

}
