package com.utsh.inventorymanagement.seed;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.model.Role;
import com.utsh.inventorymanagement.model.User;
import com.utsh.inventorymanagement.service.AuthenticationService;
import com.utsh.inventorymanagement.service.IProductsService;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Component
public class DataSeeder implements CommandLineRunner {


    private final IProductsService productsService;

    private final AuthenticationService authenticationService;



    public DataSeeder(IProductsService productsService, AuthenticationService authenticationService) {
        this.productsService = productsService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(String... args) throws Exception {
        buildAdminUser();
        createInitialProducts();
    }


    public List<ProductDTO> createInitialProducts() {
        return Arrays.asList(
                createProduct("1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Arroz", "Arroz blanco de grano largo", 2.5, 100, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("2b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Frijoles", "Frijoles negros enlatados", 1.8, 50, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("3b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Aceite de Oliva", "Aceite de oliva virgen extra", 5.0, 80, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("4b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Sal", "Sal de mesa", 1.0, 120, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("5b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Azúcar", "Azúcar refinada", 1.5, 90, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("6b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Pasta", "Pasta de trigo", 2.0, 70, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png"),
                createProduct("7b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed", "Sopa enlatada", "Sopa de tomate enlatada", 2.8, 60, "2024-03-05", "Abarrotes", "http://192.168.1.24:8080/content/test-image.png")
        );
    }

    private ProductDTO createProduct(String id, String name, String description, double price, int stock, String entryDate, String category, String image) {

        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setEntryDate(Calendar.getInstance().getTime());
        product.setEntryDate(Calendar.getInstance().getTime());
        product.setImages(List.of(image));

        productsService.createProduct(product);

        return product;
    }

    private void buildAdminUser() {
        var user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setUsername("admin@google.com");
        user.setEmail("admin@google.com");
        user.setPassword("Abc123");
        user.setRole(Role.USER);

        authenticationService.register(user);
    }
}
