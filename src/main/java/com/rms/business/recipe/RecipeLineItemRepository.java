package com.rms.business.recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecipeLineItemRepository extends CrudRepository<RecipeLineItem, Integer>, PagingAndSortingRepository<RecipeLineItem, Integer> {

}
