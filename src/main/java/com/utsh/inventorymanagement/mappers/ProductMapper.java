package com.utsh.inventorymanagement.mappers;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.dto.ProductResponse;
import com.utsh.inventorymanagement.model.Product;

public class ProductMapper {


    public static Product productDtoToProduct(ProductDTO productDto){
        Product product = new Product();
        if (productDto.getId() != null){
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setEntryDate(productDto.getEntryDate());
        product.setCategory(productDto.getCategory());
        product.setImages(productDto.getImages());
        return product;
    }

    public static ProductResponse productToResponse(Product product){
        var productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        productResponse.setEntryDate(String.valueOf(product.getEntryDate()));

        productResponse.setCategory(product.getCategory());
        productResponse.setImages(product.getImages());
        return productResponse;
    }
}
