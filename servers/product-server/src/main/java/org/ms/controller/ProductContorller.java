package org.ms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.ProductPurchaseRequest;
import org.ms.entityAndDTO.ProductPurchaseResponse;
import org.ms.entityAndDTO.ProductRequest;
import org.ms.entityAndDTO.ProductResponse;
import org.ms.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductContorller {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> requests) {
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Integer productId){
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

}
