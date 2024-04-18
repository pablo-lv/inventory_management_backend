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
        return productsRepository.findByDeletedFalse();
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
        productToSave.setDeleted(false);

        var productStored = productsRepository.findByDescriptionAndDeletedIsFalse(product.getDescription());

        if (productStored.isPresent()) {
            return new Product();
        }

        return productsRepository.save(productToSave);
    }

    @Override
    public Product updateProduct(ProductDTO product) {
        Product productToUpdate = ProductMapper.productDtoToProduct(product);
        var productStored = productsRepository.findByNameAndDescriptionAndDeletedIsFalse(
                product.getName(),
                product.getDescription());

        if (productStored.isPresent() && !productStored.get().getId().equals(product.getId())) {
            return new Product();
        }
        productToUpdate.setDeleted(false);
        return productsRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(String id) {
        var product = productsRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            productsRepository.save(product);
        }

    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var productsStored = productsRepository.findByDeletedFalse(paging);

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
