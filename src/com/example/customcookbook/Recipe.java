package com.example.customcookbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Lydia Buzzard
 * March 20, 2015
 * 
 * This class represents a Recipe object to be created within the MainActivity. Parameters include a recipe name, list of ingredients,
 * and a StringBuffer of steps. The class contains default and initial value constructors along with basic set/get methods and a
 * toString method for printing the Recipe.
 */
public class Recipe implements Parcelable
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
	
	//This method reads the next Recipe found in the StringBuffer info and adds it to the list. It returns the remaining unparsed contents of
	//the StringBuffer.
	public static StringBuffer findNext(ArrayList<Recipe> list, StringBuffer info)
	{
		try
		{
			if(info.indexOf("RECIPE:") >= 0)
			{
				Recipe next = new Recipe();
				next.setName(info.substring(info.indexOf("RECIPE:")+8, info.indexOf("INGREDIENTS:")));
				info.delete(0, info.indexOf("INGREDIENTS:"));
				System.out.println(next.name);
				next.addIngredient(info.substring(12, info.indexOf("INSTRUCTIONS:")));
				info.delete(0, info.indexOf("INSTRUCTIONS:"));
				System.out.println(next.ingredients);
				
				try
				{
					next.setSteps(info.substring(14, info.indexOf("RECIPE:")));
					info.delete(0, info.indexOf(next.steps));
				}
				catch(Exception d)
				{
					next.setSteps(info.substring(14, info.length()));
					info.delete(0, info.length());
				}
				
				System.out.println(next.steps);
				
				list.add(next);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Couldn't parse recipe.");
			e.printStackTrace();
		}
		return info;
	}
	
	public boolean contains(String keyword)
	{
		if(this.name!=null && this.ingredients!=null && this.steps!=null)
			return (this.name.contains(keyword) || this.ingredients.contains(keyword) || this.steps.contains(keyword));
		return false;
	}
	
	public Recipe(Parcel in)
	{
		System.out.println("Recipe is being created from Parcel");
		String[] data = new String[3];
		in.readStringArray(data);
		this.name = data[0];
		this.ingredients = data[1];
		this.steps = data[2];
		System.out.println(name+" "+ingredients+" "+steps);
	}
	
	public int describeContents()
	{
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) 
	{
		System.out.println("Recipe is written to Parcel");
        dest.writeStringArray(new String[] {this.name,
                                            this.ingredients,
                                            this.steps});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() 
    {
        public Recipe createFromParcel(Parcel in) 
        {
            return new Recipe(in); 
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    
	public String toString()
	{
		String result = "";
		
		result = String.format("RECIPE: %s\n", name);
		
		result+="INGREDIENTS:\n"+ingredients+"\n";
		
		result+="INSTRUCTIONS:\n"+steps+"\n"+"\n";
		
		return result;
	}

}
