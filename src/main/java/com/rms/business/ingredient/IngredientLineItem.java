package com.rms.business.ingredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IngredientLineItem extends CrudRepository<Ingredient, Integer>, PagingAndSortingRepository<Ingredient, Integer> {

}
