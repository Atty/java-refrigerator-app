package refrigerationApp.repos;

import org.springframework.data.repository.CrudRepository;
import refrigerationApp.entities.Recipe;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long>
{
	
	List<Recipe> findByIngridientsIgnoreCaseContaining(String form);
}
