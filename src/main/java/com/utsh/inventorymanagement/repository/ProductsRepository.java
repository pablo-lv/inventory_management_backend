package com.utsh.inventorymanagement.repository;

import com.utsh.inventorymanagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);


    Optional<Product> findById(String id);
}
