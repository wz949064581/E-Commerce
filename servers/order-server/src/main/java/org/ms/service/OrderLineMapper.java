package org.ms.service;

import org.ms.entityAndDTO.Order;
import org.ms.entityAndDTO.OrderLine;
import org.ms.entityAndDTO.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest request) {

        return OrderLine.builder()
                .id(request.id())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }
}
