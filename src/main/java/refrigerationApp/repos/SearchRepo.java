package refrigerationApp.repos;

import refrigerationApp.store.ProductStore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRepo {
	private static final String URL = "jdbc:postgresql://90.188.48.32:5432/Refregerator";
	
	private static ResultSet valuesFromTablePostgresDB(String name) throws SQLException {
		Connection connection = DriverManager.getConnection(URL, "postgres", "123");
		Statement  statement  = connection.createStatement();
		return statement.executeQuery("SELECT * from " + name.trim());
	}
	
	private static ArrayList<String> getListOfColumn(String name) throws SQLException {
		ResultSet         valuesRecipeTable = valuesFromTablePostgresDB("recipe");
		ArrayList<String> columnValues      = new ArrayList<>();
		while (valuesRecipeTable.next()) {
			columnValues.add(valuesRecipeTable.getString(name).toLowerCase().trim());
		}
		return columnValues;
	}
	
	private static ArrayList<String> getFinishedIngredientsListForFilter() throws SQLException {
		ArrayList<String> list = getListOfColumn("ingredients");
		ProductStore.getProducts().parallelStream().forEach(n -> {
			String productTitle = n.getProductTitle();
			for (int i = 0; i < list.size(); i++) {
				list.set(i, list.get(i).replace(productTitle, "").trim());
			}
		});
		return list;
	}
	
	public static List<String> getPositionRecipeForHardFilter() throws SQLException {
		ArrayList<String> idList          = getListOfColumn("id");
		ArrayList<String> ingredientsList = getFinishedIngredientsListForFilter();
		for (int i = 0; i < ingredientsList.size(); i++) {
			if (ingredientsList.get(i).matches(".*[а-я].*")) {
				idList.set(i, "-1");
			}
		}
		idList.removeIf(n -> n.equals("-1"));
		return idList;
	}
	
	public static List<String> getPositionRecipeForSoftFilter() throws SQLException {
		ArrayList<String> copyOfIngredientsList = getListOfColumn("ingredients");
		ArrayList<String> idList                = getListOfColumn("id");
		ArrayList<String> ingredientsList       = getFinishedIngredientsListForFilter();
		for (int i = 0; i < ingredientsList.size(); i++) {
			if (ingredientsList.get(i).matches("(.+(\\s\\S).+){2,}") ||
			    ingredientsList.get(i).equals(copyOfIngredientsList.get(i))) {
				idList.set(i, "-1");
			}
		}
		idList.removeIf(n -> n.equals("-1"));
		return idList;
	}
}