package com.example.customcookbook;

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
	ArrayList<String> ingredients;
	//Maybe add an image parameter to store photo of recipe?
	StringBuffer steps;	//If we save the image, this param may be deleted
	
	//Default constructor
	public Recipe()
	{
		//Initialize empty params
		name="";
		ingredients = new ArrayList<String>();
		steps = new StringBuffer();
		
	}
	
	//Initial value constructor
	public Recipe(String name)
	{
		this.name = name;
		ingredients = new ArrayList<String>();
		steps = new StringBuffer();
	}
	
	//SET AND GET METHODS
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addIngredient(String ingred)
	{
		ingredients.add(ingred);
	}
	
	public void setSteps(StringBuffer steps)
	{
		this.steps  = steps;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getIngredient(int index)
	{
		return this.ingredients.get(index);
	}
	
	public StringBuffer getSteps()
	{
		return this.steps;
	}
	
	public String toString()
	{
		String result = "";
		int counter = 0;
		
		result = String.format("%s:\n", name);
		
		for(counter = 0; counter < ingredients.size(); counter++)
		{
			result+=String.format("%d. %s\n", counter+1, ingredients.get(counter));
		}
		
		result+=steps;
		
		return result;
	}

}
