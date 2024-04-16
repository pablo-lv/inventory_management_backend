package com.utsh.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class Sale {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @OrderColumn(name = "id")
    private String id;

    @Column(name = "total")
    private Double total;

    @Column(name = "created_at")
    private LocalDate createdAt;


}
