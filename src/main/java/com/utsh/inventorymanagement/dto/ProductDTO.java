package com.utsh.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Date entryDate;
    private String category;
    private List<String> images;
}
