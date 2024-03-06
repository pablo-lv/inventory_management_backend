package com.utsh.inventorymanagement.mappers;

import com.utsh.inventorymanagement.dto.ProductDTO;
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
}
