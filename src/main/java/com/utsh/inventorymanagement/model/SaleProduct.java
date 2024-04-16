package com.utsh.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "sale_products")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class SaleProduct {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @OrderColumn(name = "id")
    private String id;

    @Column(name = "id_product")
    private String idProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "id_sale")
    private String idSale;


}
