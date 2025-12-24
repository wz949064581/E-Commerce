package org.ms.controller;

import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.OrderLineResponse;
import org.ms.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLinesByOrderId(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.getOrderLinesByOrderId(orderId));
    }

}
