package refrigerationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import refrigerationApp.domain.Product;
import refrigerationApp.entities.Recipe;
import refrigerationApp.repos.RecipeRepo;
import refrigerationApp.store.ProductStore;

import java.util.List;
import java.util.Map;

@Controller
public class MainController
{
	
	@Autowired
	private RecipeRepo recipeRepo;
	
	@GetMapping
	public String main(Map<String, Object> model)
	{
		return "main";
	}
	
	@PostMapping("addProduct")
	public String addProduct(@RequestParam String title, Map<String, Object> model)
	{
		if (!title.isEmpty() && title.length() != 0)
		{
			Product product = new Product(title.toLowerCase());
			ProductStore.getProducts().add(product);
			model.put("products", ProductStore.getProducts());
		}
		return "main";
	}
	
	@PostMapping("addRecipe")
	public String addRecipe(@RequestParam String nameRecipe,
	                        @RequestParam String ingridients,
	                        @RequestParam String howToCook,
	                        Map<String, Object> model)
	{
		Recipe recipe = new Recipe(nameRecipe, ingridients, howToCook);
		
		recipeRepo.save(recipe);
		
		Iterable<Recipe> recipies = recipeRepo.findAll();
		
		model.put("recipies", recipies);
		
		return "main";
	}
	
	@PostMapping("filter")
	public String filter(@RequestParam String filter, Map<String, Object> model)
	{
		List<Recipe> recipies = recipeRepo.findByIngridientsIgnoreCaseContaining(filter);
		
		model.put("recipies", recipies);
		
		return "main";
	}
}
