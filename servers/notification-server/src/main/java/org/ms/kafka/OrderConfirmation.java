package org.ms.kafka;

import org.ms.entityAndDTO.Customer;
import org.ms.entityAndDTO.PaymentMethod;
import org.ms.entityAndDTO.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
