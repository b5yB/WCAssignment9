package com.coderscampus9.WCAssignment9.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus9.WCAssignment9.models.Recipe;
import com.coderscampus9.WCAssignment9.services.FileService;

@RestController
@CrossOrigin(origins = "*")
public class RecipeController {
	
//	The “all-recipes” endpoint will not filter the data at all and should return the full Collection.
//	The “gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true
//  The “vegan” endpoint will only return a subset of the full Collection where vegan is true
//	The “vegan-and-gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true and vegan is true
//  The “vegetarian” endpoint will only return a subset of the full Collection where vegetarian is true
	
	private FileService fileService;
	
	@Autowired
	public RecipeController (FileService fs) {
		this.fileService = fs;
	}
	
	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFree () {
		return fileService.getGlutenFree("recipes.txt");
	}

	@GetMapping("/vegan")
	public List<Recipe> getVegan () {
		System.out.println("in vegan request");
		return fileService.getVegan("recipes.txt");
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFree () {
		return fileService.getVeganAndGlutenFree("recipes.txt");
	}

	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian () {
		return fileService.getVegetarian("recipes.txt");
	}

	@GetMapping("/all-recipes")
	public List<Recipe> allRecipes () {
		return fileService.getAllRecipes("recipes.txt");
	}

}
