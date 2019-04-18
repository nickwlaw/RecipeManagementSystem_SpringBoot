package com.rms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.rms.business.recipe.Recipe;
import com.rms.business.recipe.RecipeRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(recipeRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("")
	public JsonResponse getAllPaginated(@RequestParam int start, @RequestParam int limit) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(PageRequest.of(start, limit));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (recipeRepo.existsById(id))
				jr = JsonResponse.getInstance(recipeRepo.findById(id).get());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse add(@RequestBody Recipe r) {
		JsonResponse jr = null;
		try {
			if (recipeRepo.existsById(r.getId()))
				jr = JsonResponse.getInstance("Recipe already exists.");
			else
				jr = JsonResponse.getInstance(saveRecipe(r));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse update(@RequestBody Recipe r) {
		JsonResponse jr = null;
		try {
			if (recipeRepo.existsById(r.getId()))
				jr = JsonResponse.getInstance(saveRecipe(r));
			else
				jr = JsonResponse.getInstance("Recipe not found.");
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("{id}")
	public JsonResponse delete(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (recipeRepo.existsById(id)) {
				jr = JsonResponse.getInstance(recipeRepo.findById(id));
				recipeRepo.deleteById(id);
			} else
				jr = JsonResponse.getInstance("No recipe found with ID " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse saveRecipe(Recipe r) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(recipeRepo.save(r));
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive);
		}
		return jr;
	}
}
