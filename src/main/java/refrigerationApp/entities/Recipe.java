package refrigerationApp.entities;

import javax.persistence.*;

@Entity
public class Recipe {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "name_recipe")
	private String nameRecipe;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	@Column(name = "how_to_cook")
	private String howToCook;
	
	public Recipe(String nameRecipe, String ingredients, String howToCook) {
		this.nameRecipe  = nameRecipe;
		this.ingredients = ingredients;
		this.howToCook   = howToCook;
	}
	
	public Recipe() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNameRecipe() {
		return nameRecipe;
	}
	
	public void setNameRecipe(String nameRecipe) {
		this.nameRecipe = nameRecipe;
	}
	
	public String getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(String form) {
		this.ingredients = form;
	}
	
	public String getHowToCook() {
		return howToCook;
	}
	
	public void setHowToCook(String howToCook) {
		this.howToCook = howToCook;
	}
}
