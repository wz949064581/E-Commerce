package org.ms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.CustomerRequest;
import org.ms.entityAndDTO.CustomerResponse;
import org.ms.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @ResponseStatus
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exits/{customer-id}")
    @ResponseStatus
    public ResponseEntity<Boolean> findCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @GetMapping("/{customer-id}")
    @ResponseStatus
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{customer-id}")
    @ResponseStatus
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}