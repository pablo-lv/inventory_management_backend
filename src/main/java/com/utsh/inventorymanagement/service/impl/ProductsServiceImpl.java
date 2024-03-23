package com.utsh.inventorymanagement.service.impl;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.dto.ProductResponse;
import com.utsh.inventorymanagement.mappers.ProductMapper;
import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.repository.ProductsRepository;
import com.utsh.inventorymanagement.service.IProductsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductsServiceImpl implements IProductsService {

    private ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }


    @Override
    public Product getProductById(String id) {
        Product product = null;
        product = productsRepository.findById(id).orElse(null);

        return product;
    }

    @Override
    public Product createProduct(ProductDTO product) {
        Product productToSave = ProductMapper.productDtoToProduct(product);
        return productsRepository.save(productToSave);
    }

    @Override
    public Product updateProduct(ProductDTO product) {
        Product productToUpdate = ProductMapper.productDtoToProduct(product);
        return productsRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(String id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var productsStored = productsRepository.findAll(paging);

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product: productsStored.getContent()) {
            productResponses.add(ProductMapper.productToResponse(product));
        }

        return Map.of(
            "products", productResponses,
            "currentPage", productsStored.getNumber(),
            "totalItems", productsStored.getTotalElements(),
            "totalPages", productsStored.getTotalPages()
        );
    }
}
