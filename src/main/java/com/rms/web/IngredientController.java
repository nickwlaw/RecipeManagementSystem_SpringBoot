package com.rms.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.rms.business.ingredient.Ingredient;
import com.rms.business.ingredient.IngredientRepository;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientRepository ingRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(ingRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("")
	public JsonResponse getAllPaginated(@RequestParam int start, @RequestParam int limit) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(ingRepo.findAll(PageRequest.of(start, limit)));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Ingredient> ingredient = ingRepo.findById(id);
			if (ingredient.isPresent())
				jr = JsonResponse.getInstance(ingredient);
			else
				jr = JsonResponse.getInstance("No ingredient found with ID " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse add(@RequestBody Ingredient ingredient) {
		JsonResponse jr = null;
		try {
			if (ingRepo.existsById(ingredient.getId()))
				jr = JsonResponse.getInstance("Ingredient already exists.");
			else
				jr = saveIngredient(ingredient);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse update(@RequestBody Ingredient ingredient) {
		JsonResponse jr = null;
		try {
			if (ingRepo.existsById(ingredient.getId()))
				jr = saveIngredient(ingredient);
			else
				jr = JsonResponse.getInstance("Ingredient not found.");
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Ingredient> ing = ingRepo.findById(id);
			if (ing.isPresent()) {
				ingRepo.deleteById(id);
				jr = JsonResponse.getInstance(ing.get());
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse saveIngredient(Ingredient i) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(ingRepo.save(i));
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive);
		}
		return jr;
	}
}
