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
	private boolean    filter;
	
	@GetMapping("/")
	public String main(Map<String, Object> model) throws SQLException {
		SearchRepo.searchPositionOfRecipeHardFilter();
		SearchRepo.searchPositionOfRecipeSoftFilter();
		Iterable<Recipe> recipiesAll        = recipeRepo.findAll();
		Iterable<Recipe> recipiesHardFilter = recipeRepo.findByIdIn(SearchRepo.getIdList());
		Iterable<Recipe> recipiesSoftFilter = recipeRepo.findByIdIn(SearchRepo.getCopyOfIdList());
		if (ProductStore.getProducts().equals(Collections.emptyList())) {
			model.put("recipies", recipiesAll);
		}
		else {
			if (filter) {
				model.put("recipies", recipiesSoftFilter);
			}
			else {
				model.put("recipies", recipiesHardFilter);
			}
		}
		model.put("products", ProductStore.getProducts());
		
		return "main";
	}
	
	@PostMapping("softFilter")
	public String softFilter(@RequestParam(defaultValue = "false") boolean softFilter, Map<String, Object> model) {
		filter = softFilter;
		
		return "redirect:/";
	}
	
	@PostMapping("addProduct")
	public String addProduct(@RequestParam String title, Map<String, Object> model) {
		if (!title.isEmpty()) {
			Product product = new Product(title.toLowerCase());
			ProductStore.getProducts().add(product);
			model.put("products", ProductStore.getProducts());
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/deleteProduct/{productId}")
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
}
