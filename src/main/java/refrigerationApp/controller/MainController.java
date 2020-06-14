package refrigerationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import refrigerationApp.entities.Product;
import refrigerationApp.entities.Recipe;
import refrigerationApp.repos.RecipeRepo;
import refrigerationApp.repos.SearchRepo;
import refrigerationApp.store.ProductStore;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MainController {
	
	@Autowired
	private RecipeRepo recipeRepo;
	
	@GetMapping
	public String main(Map<String, Object> model) throws SQLException {
		SearchRepo.searchPositionOfRecipe();
		Iterable<Recipe> recipiesAll    = recipeRepo.findAll();
		Iterable<Recipe> recipiesFilter = recipeRepo.findByIdIn(SearchRepo.getIdList());
		if (recipiesFilter.equals(Collections.emptyList())) {
			model.put("recipies", recipiesAll);
		}
		else {
			model.put("recipies", recipiesFilter);
		}
		model.put("products", ProductStore.getProducts());
		
		return "main";
	}
	
	
	@PostMapping("addProduct")
	public String addProduct(@RequestParam String title, Map<String, Object> model) {
		if (!title.isEmpty() && title.length() != 0) {
			Product product = new Product(title.toLowerCase());
			ProductStore.getProducts().add(product);
			model.put("products", ProductStore.getProducts());
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value = {"/deleteProduct/{productId}"})
	public String deleteProductById(@PathVariable int productId, Map<String, Object> model) {
		Iterator<Product> productsList = ProductStore.getProducts().iterator();
		while (productsList.hasNext()) {
			if (productsList.next().getProductId() == productId) {
				productsList.remove();
				break;
			}
		}
		
		return "redirect:/";
	}
	
	@PostMapping("addRecipe")
	public String addRecipe(@RequestParam String nameRecipe,
	                        @RequestParam String ingridients,
	                        @RequestParam String howToCook,
	                        Map<String, Object> model) {
		Recipe recipe = new Recipe(nameRecipe, ingridients, howToCook);
		recipeRepo.save(recipe);
		Iterable<Recipe> recipies = recipeRepo.findAll();
		model.put("recipies", recipies);
		
		return "redirect:/";
	}
	
	@PostMapping("filter")
	public String filter(Map<String, Object> model) throws SQLException {
		SearchRepo.searchPositionOfRecipe();
		Iterable<Recipe> recipies = recipeRepo.findByIdIn(SearchRepo.getIdList());
		model.put("recipies", recipies);
		
		return "redirect:/";
	}
}
