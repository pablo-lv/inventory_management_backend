package com.utsh.inventorymanagement.service;

import com.utsh.inventorymanagement.dto.SaleRequest;
import com.utsh.inventorymanagement.dto.SaleResponse;

import java.util.List;

public interface SalesService {

    void saveSale(SaleRequest saleRequest);

    List<SaleResponse> getAllSales();
}
