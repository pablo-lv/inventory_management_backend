package com.utsh.inventorymanagement.controller;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.service.IProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private IProductsService productsService;

    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Product>> getAllProducts() {
//        Iterable<Product> products = productsService.getAllProducts();
        List<Product> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product prod = productsService.getProductById(id);

        if (prod != null) {
            return new ResponseEntity<>(prod, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product) {
        Product prod = productsService.createProduct(product);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product) {
        Product prod = productsService.updateProduct(product);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Map<String, Object> response = productsService.getAll(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Iterable<Product>> getAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Map<String, Object> response = productsService.getAll(page, size);
        List<Product> products = (List<Product>) response.get("products");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
