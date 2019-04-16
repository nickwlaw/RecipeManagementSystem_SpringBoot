package com.rms.business.ingredient;

import javax.persistence.*;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ingredient;
	
	public Ingredient(int id, String ingredient) {
		this.id = id;
		this.ingredient = ingredient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", ingredient=" + ingredient + "]";
	}
	
}
