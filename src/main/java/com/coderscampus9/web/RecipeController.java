package com.coderscampus9.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus9.models.Recipe;
import com.coderscampus9.services.FileService;

@RestController
public class RecipeController {
	
//	The “all-recipes” endpoint will not filter the data at all and should return the full Collection.
//	The “gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true
//  The “vegan” endpoint will only return a subset of the full Collection where vegan is true
//	The “vegan-and-gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true and vegan is true
//  The “vegetarian” endpoint will only return a subset of the full Collection where vegetarian is true
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFree () throws IOException {
		return fileService.getGlutenFree("recipes.txt");
	}

	@GetMapping("/vegan")
	public List<Recipe> getVegan () throws IOException {
		return fileService.getVegan("recipes.txt");
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFree () throws IOException {
		return fileService.getVeganAndGlutenFree("recipes.txt");
	}

	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian () throws IOException {
		return fileService.getVegetarian("recipes.txt");
	}

	@GetMapping("/all-recipes")
	public List<Recipe> allRecipes () throws IOException {
		return fileService.getAllRecipes("recipes.txt");
	}

}
