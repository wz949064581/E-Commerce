package org.ms.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.*;
import org.ms.kafka.OrderConfirmation;
import org.ms.kafka.OrderProducer;
import org.ms.repository.CustomerClient;
import org.ms.repository.OrderRepository;
import org.ms.repository.PaymentClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot create order for non-existing customer")
        );

        List<PurchaseResponse> purchaseProducts = this.productClient.purchaseProducts(request.products());
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

        //asdf

        PaymentRequest paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer, purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return repo.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Integer orderId) {
        return repo.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found")
        );
    }
}
