package com.utsh.inventorymanagement.service;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IProductsService {

    List<Product> getAllProducts();

    Map<String, Object> getAll(Integer page, Integer size);
    Product getProductById(String id);
    Product createProduct(ProductDTO product);
    Product updateProduct(ProductDTO product);
    void deleteProduct(String id);
}
