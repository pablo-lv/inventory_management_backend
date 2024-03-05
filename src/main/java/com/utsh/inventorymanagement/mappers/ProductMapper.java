package com.utsh.inventorymanagement.mappers;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.model.Product;

public class ProductMapper {


    public static Product productDtoToProduct(ProductDTO productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setEntryDate(productDto.getEntryDate());
        product.setCategory(productDto.getCategory());
        return product;
    }
}
