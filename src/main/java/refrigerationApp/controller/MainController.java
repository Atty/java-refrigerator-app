package refrigerationApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import refrigerationApp.domain.Product;
import refrigerationApp.store.ProductStore;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping
    public String main(Map<String, Object> model) {
        return "main";
    }

    @PostMapping
    public String addProduct(@RequestParam String title, Map<String, Object> model) {
        if (!title.isEmpty() && title.length() != 0) {
            Product product = new Product(title.toLowerCase());
            ProductStore.getProducts().add(product);
            model.put("products", ProductStore.getProducts());
        }
        return "main";
    }
}
