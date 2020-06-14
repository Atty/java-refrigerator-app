package refrigerationApp.repos;

import com.sun.istack.NotNull;
import org.springframework.data.repository.CrudRepository;
import refrigerationApp.entities.Recipe;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {
	
	List<Recipe> findByIdIn(@NotNull List<String> list);
}
