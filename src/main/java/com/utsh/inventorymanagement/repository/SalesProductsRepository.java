package com.utsh.inventorymanagement.repository;

import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.model.SaleProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesProductsRepository extends JpaRepository<SaleProduct, String> {

    List<SaleProduct> findAll();

    Page<SaleProduct> findAll(Pageable pageable);

    Optional<SaleProduct> findById(String id);

    List<SaleProduct> findAllByIdSale(String id);
}
