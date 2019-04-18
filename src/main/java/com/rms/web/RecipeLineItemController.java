package com.rms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.rms.business.recipe.RecipeLineItem;
import com.rms.business.recipe.RecipeLineItemRepository;

@RestController
@RequestMapping("/recipe-line-items")
public class RecipeLineItemController {

	@Autowired
	private RecipeLineItemRepository rliRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(rliRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("")
	public JsonResponse getAllPaginated(@RequestParam int start, @RequestParam int limit) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(rliRepo.findAll(PageRequest.of(start, limit)));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (rliRepo.existsById(id))
				jr = JsonResponse.getInstance(rliRepo.findById(id));
			else
				jr = JsonResponse.getInstance("Recipe line item not found.");
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse add(@RequestBody RecipeLineItem rli) {
		JsonResponse jr = null;
		try {
			if (rliRepo.existsById(rli.getId()))
				jr = JsonResponse.getInstance("Recipe line item already exists.");
			else
				jr = JsonResponse.getInstance(saveRLI(rli));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse update(@RequestBody RecipeLineItem rli) {
		JsonResponse jr = null;
		try {
			if (rliRepo.existsById(rli.getId()))
				jr = JsonResponse.getInstance(saveRLI(rli));
			else
				jr = JsonResponse.getInstance("Recipe line item not found.");
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (rliRepo.existsById(id)) {
				jr = JsonResponse.getInstance(rliRepo.findById(id));
				rliRepo.deleteById(id);
			} else
				jr = JsonResponse.getInstance("Recipe line item not found.");
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse saveRLI(RecipeLineItem rli) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(rliRepo.save(rli));
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive);
		}
		return jr;
	}
}
