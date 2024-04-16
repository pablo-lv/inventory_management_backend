package com.utsh.inventorymanagement.service.impl;

import com.utsh.inventorymanagement.dto.ProductSale;
import com.utsh.inventorymanagement.dto.SaleRequest;
import com.utsh.inventorymanagement.dto.SaleResponse;
import com.utsh.inventorymanagement.model.Product;
import com.utsh.inventorymanagement.model.Sale;
import com.utsh.inventorymanagement.model.SaleProduct;
import com.utsh.inventorymanagement.repository.ProductsRepository;
import com.utsh.inventorymanagement.repository.SalesProductsRepository;
import com.utsh.inventorymanagement.repository.SalesRepository;
import com.utsh.inventorymanagement.service.SalesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;
    private SalesProductsRepository salesProductsRepository;

    private ProductsRepository productsRepository;

    public SalesServiceImpl(SalesRepository salesRepository, SalesProductsRepository salesProductsRepository, ProductsRepository productsRepository) {
        this.salesRepository = salesRepository;
        this.salesProductsRepository = salesProductsRepository;
        this.productsRepository = productsRepository;
    }

    public void saveSale(SaleRequest saleRequest) {
        List<ProductSale> productSales = saleRequest.getProducts();

        // Create and store the Sale entity
        Sale sale = new Sale();
        sale.setTotal(saleRequest.getTotal());
        sale.setCreatedAt(LocalDate.now());
        var storedSale = salesRepository.save(sale);

        // Prepare SaleProduct entries and update stock
        List<SaleProduct> saleProducts = new ArrayList<>();
        for (ProductSale productSale : productSales) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setIdSale(storedSale.getId());
            saleProduct.setIdProduct(productSale.getId());
            saleProduct.setQuantity(productSale.getQuantity());
            saleProducts.add(saleProduct);

            // Update stock
            Product product = productsRepository.findById(productSale.getId()).orElseThrow(() -> new RuntimeException("Product not found: " + productSale.getId()));
            int newStock = product.getStock() - productSale.getQuantity();
            if (newStock >= 0) {
                product.setStock(newStock);
                productsRepository.save(product);
            } else {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
        }

        // Save all the SaleProduct entries
        salesProductsRepository.saveAll(saleProducts);
    }



    @Override
    public List<SaleResponse> getAllSales() {
        List<Sale> sales = salesRepository.findAll();

        List<SaleResponse> saleResponses = sales.stream().map(sale -> {
            SaleResponse saleResponse = new SaleResponse();
            saleResponse.setIdSale(sale.getId());
            saleResponse.setTotal(sale.getTotal());
            saleResponse.setCreatedAt(sale.getCreatedAt());

            List<SaleProduct> saleProducts = salesProductsRepository.findAllByIdSale(sale.getId());

            // Create a map of product IDs and their total quantities
            Map<String, Integer> productIdToQuantityMap = saleProducts.stream()
                    .collect(Collectors.groupingBy(
                            SaleProduct::getIdProduct,
                            Collectors.summingInt(SaleProduct::getQuantity)
                    ));

            // Fetch products based on product IDs
            List<Product> products = productsRepository.findAllById(new ArrayList<>(productIdToQuantityMap.keySet()));

            // Create a map of product names to total quantities
            Map<String, Integer> productNameToQuantityMap = products.stream()
                    .collect(Collectors.toMap(
                            Product::getName,
                            product -> productIdToQuantityMap.get(product.getId()),
                            Integer::sum // In case different IDs have the same product name
                    ));

            // Format and concatenate product names with their quantities
            String concatenatedProductNames = productNameToQuantityMap.entrySet().stream()
                    .map(entry -> entry.getKey() + " (" + entry.getValue() + ")")
                    .collect(Collectors.joining(", "));


            saleResponse.setProductNames(concatenatedProductNames);
            return saleResponse;
        }).toList();

        return saleResponses;

    }
}