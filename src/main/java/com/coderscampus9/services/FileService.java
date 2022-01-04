package com.coderscampus9.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus9.models.Recipe;

@Service
public class FileService {

//	For this assignment, you’ll need to ingest a recipe.txt file and store this information in a Java Collection of your choice.
//	You will need to use the Apache Commons CSV project to parse the text file. 
//	The final video in this week’s lessons shows you how to import the Apache Commons CSV project.
//	You may also need to create a Configuration class, but that will depend on how you structure your code. 
		
////Cooking Minutes, Dairy Free, Gluten Free, Instructions, Preparation Minutes, Price Per Serving, 
////Ready In Minutes, Servings, Spoonacular Score, Title, Vegan, Vegetarian
//
////10, true, true, "Preheat oven to 450 degrees. Arrange salmon in a shallow baking pan. Season with salt and pepper. "
////+ "Roast until no longer pink in the middle and flaky, 10 to 13 minutes. Using a flat spatula, remove fillets, "
////+ "leaving skin on the baking sheet.", 5.0, 427.92, 15, 4, 99.0, "Roasted Salmon With Lime And Cilantro", false, false

	private List<Recipe> readRecipes (String fileSource) throws IOException {
		List<Recipe> allRecipes = new ArrayList<Recipe>();
		
		Reader in = new FileReader (fileSource);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\').withIgnoreSurroundingSpaces()
											   .withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", 
													       "Preparation Minutes", "Price Per Serving", "Ready In Minutes", 
													       "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
											   .withSkipHeaderRecord()
											   .parse(in);
		
		for (CSVRecord record : records) {
			int cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
			boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
			boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
			String instructions = (record.get("Instructions"));
			double preparationMinutes = Double.parseDouble(record.get("Preparation Minutes"));
			double pricePerServing = Double.parseDouble(record.get("Price Per Serving"));
			int readyInMinutes = Integer.parseInt(record.get("Ready In Minutes"));
			int servings = Integer.parseInt(record.get("Servings"));
			double spoonacularScore = Double.parseDouble(record.get("Spoonacular Score"));
			String title = (record.get("Title"));
			boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
			boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));
			
			Recipe r = new Recipe(cookingMinutes, dairyFree, glutenFree, instructions, preparationMinutes, pricePerServing,
								  readyInMinutes, servings, spoonacularScore, title, vegan, vegetarian);
			
			allRecipes.add(r);
			
		}
		
		return allRecipes;
		
	}
	
	public List<Recipe> getGlutenFree (String sourceFile) throws IOException {
		return readRecipes(sourceFile).stream()
									  .filter(recipe -> recipe.getGlutenFree() == true)
									  .toList();
	}
	
	public List<Recipe> getVegan (String sourceFile) throws IOException {
		return readRecipes(sourceFile).stream()
									  .filter(recipe -> recipe.getVegan() == true)
									  .toList();
	}
	
	public List<Recipe> getVeganAndGlutenFree (String sourceFile) throws IOException {
		return readRecipes(sourceFile).stream()
									  .filter(recipe -> recipe.getVegan() == true && recipe.getGlutenFree() == true)
									  .toList();
	}
	
	public List<Recipe> getVegetarian (String sourceFile) throws IOException {
		return readRecipes(sourceFile).stream()
									  .filter(recipe -> recipe.getVegetarian() == true)
									  .toList();
	}
	
	public List<Recipe> getAllRecipes (String sourceFile) throws IOException {
		return readRecipes(sourceFile);
	}

}
