package com.utsh.inventorymanagement.repository;

import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sale, String> {

    List<Sale> findAll();

    Page<Sale> findAll(Pageable pageable);


    Optional<Sale> findById(String id);
}
