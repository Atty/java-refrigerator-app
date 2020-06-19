package refrigerationApp.repos;

import refrigerationApp.entities.Product;
import refrigerationApp.store.ProductStore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRepo {
	
	private static final String       URL = "jdbc:postgresql://90.188.48.32:5432/Refregerator";
	private static       List<String> ingredientsList;
	private static       List<String> copyOfIngredientsList;
	private static       List<String> idList;
	private static       List<String> copyOfIdList;
	private static       Connection   connection;
	
	private static void postgresDBToArray() throws SQLException {
		connection = DriverManager.getConnection(URL, "postgres", "123");
		Statement statement     = connection.createStatement();
		ResultSet columnsFromDB = statement.executeQuery("SELECT * from recipe");
		idList                = new ArrayList<>();
		copyOfIdList          = new ArrayList<>();
		ingredientsList       = new ArrayList<>();
		copyOfIngredientsList = new ArrayList<>();
		while (columnsFromDB.next()) {
			idList.add(columnsFromDB.getString("id").toLowerCase().trim());
			ingredientsList.add(columnsFromDB.getString("ingredients").toLowerCase().trim());
		}
		copyOfIdList.addAll(idList);
		copyOfIngredientsList.addAll(ingredientsList);
	}
	
	public static void searchPositionOfRecipeHardFilter() throws SQLException {
		postgresDBToArray();
		for (Product product : ProductStore.getProducts()) {
			String productTitle = product.getProductTitle();
			for (int i = 0; i < ingredientsList.size(); i++) {
				ingredientsList.set(i, ingredientsList.get(i).replace(productTitle, "").trim());
			}
		}
		for (int i = 0; i < ingredientsList.size(); i++) {
			if (ingredientsList.get(i).matches(".*[а-я].*")) {
				idList.set(i, "-1");
			}
		}
		idList.removeIf(n -> n.equals("-1"));
	}
	
	public static void searchPositionOfRecipeSoftFilter() {
		for (int i = 0; i < ingredientsList.size(); i++) {
			if (ingredientsList.get(i).matches("(.+(\\s\\S).+){2,}") ||
			    ingredientsList.get(i).equals(copyOfIngredientsList.get(i))) {
				copyOfIdList.set(i, "-1");
			}
		}
		copyOfIdList.removeIf(n -> n.equals("-1"));
	}
	
	public static List<String> getIdList() {
		return idList;
	}
	
	public static List<String> getCopyOfIdList() {
		return copyOfIdList;
	}
}
