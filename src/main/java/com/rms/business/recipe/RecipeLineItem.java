package com.rms.business.recipe;

import javax.persistence.*;

import com.rms.business.ingredient.Ingredient;

@Entity
public class RecipeLineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "RecipeID")
	private Recipe recipe;
	@ManyToOne
	@JoinColumn(name = "IngredientID")
	private Ingredient ingredient;
	private double quantity;
	private String unit;
	
	public RecipeLineItem (int id, Recipe recipe, Ingredient ingredient, double quantity, String unit) {
		this.id = id;
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "RecipeLineItem [id=" + id + ", recipe=" + recipe + ", ingredient=" + ingredient + ", quantity="
				+ quantity + ", unit=" + unit + "]";
	}
	
}
