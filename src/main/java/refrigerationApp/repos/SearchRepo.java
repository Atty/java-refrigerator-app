package refrigerationApp.repos;

import refrigerationApp.entities.Product;
import refrigerationApp.store.ProductStore;

import java.sql.*;
import java.util.ArrayList;

public class SearchRepo {
	
	private static final String            URL = "jdbc:postgresql://90.188.48.32:5432/Refregerator";
	private static       ArrayList<String> ingredientsList;
	private static       ArrayList<String> idList;
	private static       Connection        connection;
	
	private static void postgresDBToArray() throws SQLException {
		connection = DriverManager.getConnection(URL, "postgres", "123");
		Statement statement     = connection.createStatement();
		ResultSet columnsFromDB = statement.executeQuery("SELECT * from recipe");
		idList          = new ArrayList<>();
		ingredientsList = new ArrayList<>();
		while (columnsFromDB.next()) {
			idList.add(columnsFromDB.getString("id").toLowerCase().trim());
			ingredientsList.add(columnsFromDB.getString("ingredients").toLowerCase().trim());
		}
	}
	
	public static void searchPositionOfRecipeHardSearch() throws SQLException {
		postgresDBToArray();
		for (Product product : ProductStore.getProducts()) {
			String productTitle = product.getProductTitle().toLowerCase().trim();
			for (int i = 0; i < ingredientsList.size(); i++) {
				ingredientsList.set(i, ingredientsList.get(i).replace(productTitle, ""));
			}
		}
		for (int i = 0; i < ingredientsList.size(); i++) {
			if (ingredientsList.get(i).matches(".*[а-я].*")) {
				idList.set(i, "-1");
			}
		}
		idList.removeIf(n -> n.equals("-1"));
	}
	
	public static ArrayList<String> getIdList() {
		return idList;
	}
}
