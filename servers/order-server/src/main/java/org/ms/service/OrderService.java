package org.ms.service;

import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.*;
import org.ms.exception.BusinessException;
import org.ms.repository.CustomerClient;
import org.ms.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {

        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(
                () -> new BusinessException("Cannot create order for non-existing customer")
        );

        this.productClient.purchaseProducts(request.products());
        Order order = this.repo.save(mapper.toOrder(request));

        for(PurchaseRequest pr : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            pr.productId(),
                            pr.quantity()
                    )
            );
        }


        return null;
    }
}
