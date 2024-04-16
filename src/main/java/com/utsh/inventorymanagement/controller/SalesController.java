package com.utsh.inventorymanagement.controller;

import com.utsh.inventorymanagement.dto.ProductDTO;
import com.utsh.inventorymanagement.dto.ProductResponse;
import com.utsh.inventorymanagement.dto.SaleRequest;
import com.utsh.inventorymanagement.dto.SaleResponse;
import com.utsh.inventorymanagement.mappers.ProductMapper;
import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.service.IProductsService;
import com.utsh.inventorymanagement.service.SalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class SalesController {

    private SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }


    @PostMapping()
    public ResponseEntity<String> saveSale(@RequestBody SaleRequest request) {
        salesService.saveSale(request);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        List<SaleResponse> response = salesService.getAllSales();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
