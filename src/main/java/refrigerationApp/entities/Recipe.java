package refrigerationApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nameRecipe;
	private String ingridients;
	private String howToCook;
	
	public Recipe(String nameRecipe, String ingridients, String howToCook)
	{
		this.nameRecipe = nameRecipe;
		this.ingridients = ingridients;
		this.howToCook = howToCook;
	}
	
	public Recipe()
	{
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getNameRecipe()
	{
		return nameRecipe;
	}
	
	public void setNameRecipe(String nameRecipe)
	{
		this.nameRecipe = nameRecipe;
	}
	
	public String getIngridients()
	{
		return ingridients;
	}
	
	public void setIngridients(String form)
	{
		this.ingridients = form;
	}
	
	public String getHowToCook()
	{
		return howToCook;
	}
	
	public void setHowToCook(String howToCook)
	{
		this.howToCook = howToCook;
	}
}
