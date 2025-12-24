package org.ms.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.*;
import org.ms.exception.ProductPurchaseException;
import org.ms.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {

        Product product = mapper.toProduct(request);
        return repo.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {

        List<Integer> productIds = requests.stream()
                .map(ProductPurchaseRequest::productId).toList();
        List<Product> products = repo.findAllByIdInOrderById(productIds);
        if(productIds.size() != products.size()){
            throw new ProductPurchaseException("One or more products not found for the given IDs.");
        }
        List<ProductPurchaseRequest> requestProducts = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        List<ProductPurchaseResponse> responses = new ArrayList<>();
        for (int i=0;i<products.size();i++){
            Product product = products.get(i);
            ProductPurchaseRequest productPurchaseRequest = requestProducts.get(i);
            if(product.getAvailableQuantity() < productPurchaseRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for product ID: " + product.getId());
            }
            double newQuantity = product.getAvailableQuantity() - productPurchaseRequest.quantity();
            product.setAvailableQuantity(newQuantity);
            repo.save(product);
            responses.add(mapper.toProductPurchaseResponse(product, productPurchaseRequest.quantity()));
        }

        return responses;
    }

    public ProductResponse getProductById(Integer productId) {

        return repo.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> getAllProducts() {

        return repo.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
