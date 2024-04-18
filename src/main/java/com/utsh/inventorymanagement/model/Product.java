package com.utsh.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Product {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @OrderColumn(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "category")
    private String category;

//    @ElementCollection
    @Column(name = "images")
    private List<String> images;

    @Column(name = "deleted")
    private Boolean deleted;


}
