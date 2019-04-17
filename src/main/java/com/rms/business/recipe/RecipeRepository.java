package com.rms.business.recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>, PagingAndSortingRepository<Recipe, Integer> {

}
